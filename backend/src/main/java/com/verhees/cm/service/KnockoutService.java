package com.verhees.cm.service;

import com.verhees.cm.model.competition.Knockout;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.request.CreateKnockoutRequest;
import com.verhees.cm.model.request.GameRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.KnockoutStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.repository.KnockoutRepository;
import com.verhees.cm.service.util.TournamentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class KnockoutService {

    @Autowired
    private KnockoutRepository knockoutRepository;

    public Knockout create(CreateKnockoutRequest request) {
        int stageIndex = 0;
        List<KnockoutStage> stages = new ArrayList<>();

        KnockoutStage stage = KnockoutStage.builder()
                .games(createStageGames(request.getMatches()))
                .stageIndex(stageIndex)
                .build();

        stages.add(stage);

        createEmptyStages(stageIndex, stages, stage);

        return knockoutRepository.save(Knockout.builder()
                .maxDate(request.getMaxDate())
                .name(request.getName())
                .stages(stages)
                .build());
    }

    private void createEmptyStages(int stageIndex, List<KnockoutStage> stages, KnockoutStage stage) {
        int size = stage.getGames().size();
        while (size > 1) {
            int gameIndex = 0;
            stageIndex++;

            size = (size / 2) + (size % 2);

            List<Game> games = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                games.add(Game.builder()
                        .gameIndex(gameIndex)
                        .build());
                gameIndex++;
            }

            stages.add(KnockoutStage.builder()
                    .games(games)
                    .stageIndex(stageIndex)
                    .build());
        }
    }

    public List<Game> createStageGames(List<GameRequest> list) {
        int index = 0;
        List<Game> gameList = new ArrayList<>();

        for (GameRequest request : list) {
            gameList.add(Game.of(request, index));
            index++;
        }
        return gameList;
    }

    public Knockout get(String id) {
        return knockoutRepository.findById(id)
                .orElseThrow();
    }

    public Knockout generateNextStage(int stageIndex, String knockoutID) {
        Knockout knockout = knockoutRepository.findById(knockoutID)
                .orElseThrow();

        ArrayDeque<Team> deque = knockout.getStages()
                .stream()
                .filter(stage -> stage.getStageIndex() == stageIndex)
                .findFirst()
                .orElseThrow()
                .getGames()
                .stream()
                .map(Game::calculateWinner)
                .collect(Collectors.toCollection(ArrayDeque::new));

        knockout.getStages()
                .stream()
                .filter(stage -> stage.getStageIndex() == stageIndex + 1)
                .min(Comparator.comparingInt(KnockoutStage::getStageIndex))
                .orElseThrow()
                .getGames()
                .forEach(game -> {
                    game.setTeamOne(Score.builder()
                            .team(ofNullable(deque.pollFirst()).orElse(Team.builder()
                                    .name("GEEN_TEGENSTANDER")
                                    .build()))
                            .build());
                    game.setTeamTwo(Score.builder()
                            .team(ofNullable(deque.pollFirst()).orElse(Team.builder()
                                    .name("GEEN_TEGENSTANDER")
                                    .build()))
                            .build());
                });

        return knockoutRepository.save(knockout);
    }

    public Set<String> getMostCorrectPredictors(String id) {
        List<GamePrediction> list = new ArrayList<>();
        knockoutRepository.findById(id)
                .orElseThrow()
                .getStages()
                .stream()
                .map(KnockoutStage::getGames)
                .flatMap(List::stream)
                .forEach(game -> list.addAll(game.getPredictions()));
        return ofNullable(TournamentUtil.getPredictorsByValue(list))
                .map(map -> map.size() > 0 ? TournamentUtil.getKeys(map, Collections.max(map.values())) : new HashSet<String>())
                .orElse(new HashSet<>());
    }
}
