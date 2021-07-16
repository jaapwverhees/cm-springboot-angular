package com.verhees.cm.service;

import com.google.common.collect.Sets;
import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.model.exceptions.DrawException;
import com.verhees.cm.model.exceptions.NoScoreException;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.prediction.Prediction;
import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.model.request.SetScoreRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.repository.ScoreRepository;
import com.verhees.cm.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.verhees.cm.service.util.TournamentUtil.getKeys;
import static com.verhees.cm.service.util.TournamentUtil.getPredictorsByValue;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament createTournament(CreateTournamentRequest request) {
        return tournamentRepository.save(Tournament.builder()
                .name(request.getName())
                .maxDate(request.getDate())
                .games(generateGames(request.getTeams().stream()
                        .map(team -> Team.builder()
                                .name(team)
                                .build())
                        .collect(toSet()))
                        .stream()
                        .map(set -> {
                            Team[] teams = new Team[set.size()];
                            set.toArray(teams);
                            return Game.builder()
                                    .teamOne(Score.builder()
                                            .team(teams[0])
                                            .build())
                                    .teamTwo(Score.builder()
                                            .team(teams[1])
                                            .build())
                                    .build();
                        })
                        .collect(toSet()))
                .build());
    }

    public Tournament getTournament(String id) {

        return tournamentRepository.findById(id)
                .orElseThrow();
    }

    public Set<Set<Team>> generateGames(Set<Team> teams) {
        return Sets.combinations(teams, 2);
    }

    public Set<String> getMostCorrectPredictors(String id) {
        List<GamePrediction> list = new ArrayList<>();
        tournamentRepository.findById(id)
                .orElseThrow()
                .getGames()
                .forEach(game -> list.addAll(game.getPredictions()));
        return ofNullable(getPredictorsByValue(list))
                .map(map -> map.size() > 0 ? getKeys(map, Collections.max(map.values())) : new HashSet<String>())
                .orElse(new HashSet<>());
    }
}
