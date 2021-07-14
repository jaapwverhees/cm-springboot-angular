package com.verhees.cm.service;

import com.verhees.cm.model.exceptions.IllegalDateException;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.repository.TeamRepository;
import com.verhees.cm.repository.TimeTrailRepository;
import com.verhees.cm.repository.TimeTrailStageRepository;
import com.verhees.cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.verhees.cm.util.DateValidation.checkDateAfterCurrent;
import static com.verhees.cm.util.DateValidation.checkDateBeforeCurrent;
import static java.util.stream.Collectors.toList;

@Service
public class TimeTrialStageService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TimeTrailStageRepository timeTrailStageRepository;

    @Autowired
    private UserRepository userRepository;

    public Team placeBet(String userID, Long stageID, Long teamID) {
        Team team = teamRepository.findById(teamID)
                .orElseThrow();
        User user = userRepository.findByUserCredentialsUsername(userID)
                .orElseThrow();


        timeTrailStageRepository.save(timeTrailStageRepository.findById(stageID).map(stage -> {

            checkDateBeforeCurrent(stage.getDate());

            stage.getPredictions()
                    .removeIf(prediction -> prediction.getUser().equals(user));

            stage.getPredictions()
                    .add(TimeTrialStagePrediction.builder()
                            .stage(stage)
                            .user(user)
                            .team(team)
                            .build());

            return stage;

        }).orElseThrow());

        return team;
    }

    public Optional<TimeTrialStagePrediction> getPrediction(String username, Long stageID) {
        return timeTrailStageRepository.findById(stageID)
                .flatMap(stage -> stage.getPredictions()
                        .stream()
                        .filter(prediction -> prediction.getUser()
                                .getUserCredentials()
                                .getUsername()
                                .equals(username))
                        .findFirst());

    }

    public Optional<Team> getWinner(Long stageID) {
        return timeTrailStageRepository.findById(stageID).map(TimeTrialStage::getWinner);
    }

    public Optional<Team> setWinner(Long stageID, Long teamID) {
        return timeTrailStageRepository.findById(stageID)
                .map(stage -> {

                    checkDateAfterCurrent(stage.getDate());

                    Team winner = stage.getTeams()
                            .stream()
                            .filter(team -> team.getId()
                                    .equals(teamID))
                            .findFirst()
                            .orElseThrow();

                    stage.setWinner(winner);

                    timeTrailStageRepository.save(stage);

                    return winner;
                });
    }

    public List<String> getCorrectPredictions(Long stageID) {
        return timeTrailStageRepository.findById(stageID)
                .map(timeTrialStage -> timeTrialStage.getPredictions()
                        .stream()
                        .filter(prediction -> prediction.getTeam()
                                .equals(timeTrialStage.getWinner()))
                        .map(prediction -> prediction.getUser()
                                .getUserCredentials()
                                .getUsername()).collect(toList()))
                .orElse(new ArrayList<>());
    }
}
