package com.verhees.cm.service;

import com.verhees.cm.model.exceptions.DrawException;
import com.verhees.cm.model.exceptions.NoScoreException;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.request.SetScoreRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.verhees.cm.util.DateValidation.checkDateAfterCurrent;
import static com.verhees.cm.util.DateValidation.checkDateBeforeCurrent;
import static java.util.stream.Collectors.toList;

@Service
public class GameService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public Team placeBet(String userID, Long stageID, Long teamID) {
        Team team = teamRepository.findById(teamID)
                .orElseThrow();
        User user = userRepository.findByUserCredentialsUsername(userID)
                .orElseThrow();


        gameRepository.save(gameRepository.findById(stageID).map(game -> {

            //checkDateBeforeCurrent(stage.getDate());

            game.getPredictions()
                    .removeIf(prediction -> prediction.getUser().equals(user));

            game.getPredictions()
                    .add(GamePrediction.builder()
                            .game(game)
                            .user(user)
                            .team(team)
                            .build());

            return game;

        }).orElseThrow());

        return team;
    }

    public Optional<GamePrediction> getPrediction(String username, Long stageID) {
        return gameRepository.findById(stageID)
                .flatMap(game -> game.getPredictions()
                        .stream()
                        .filter(prediction -> prediction.getUser()
                                .getUserCredentials()
                                .getUsername()
                                .equals(username))
                        .findFirst());

    }

    public List<String> getCorrectPredictions(Long stageID) {
        return gameRepository.findById(stageID)
                .map(game -> game.getPredictions()
                        .stream()
                        .filter(prediction -> prediction.getTeam()
                                .equals(calculateWinner(game)))
                        .map(prediction -> prediction.getUser()
                                .getUserCredentials()
                                .getUsername()).collect(toList()))
                .orElse(new ArrayList<>());

    }

    public Team calculateWinner(Game game){
        try {
            return game.calculateWinner();
        } catch (DrawException | NoScoreException e) {
            return new Team();
        }
    }

    public Score setScore(SetScoreRequest request) {
        Score score = scoreRepository.findById(request.getScoreID())
                .orElseThrow();

        score.setScore(request.getValue());

        return scoreRepository.save(score);
    }
}
