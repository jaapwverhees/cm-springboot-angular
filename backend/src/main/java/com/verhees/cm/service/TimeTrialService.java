package com.verhees.cm.service;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class TimeTrialService {

    @Autowired
    private TimeTrailRepository timeTrailRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TimeTrailStageRepository timeTrailStageRepository;

    @Autowired
    private UserRepository userRepository;

    public TimeTrail createTimeTrail(CreateTimeTrailRequest request) {
        List<Team> teams = request.getTeams().stream()
                .map(team -> Team.builder()
                        .name(team)
                        .build())
                .collect(toList());

        return timeTrailRepository.save(TimeTrail.builder()
                .name(request.getName())
                .stages(request.getStages().stream().map(date -> TimeTrialStage.builder()
                        .date(date)
                        .teams(teams)
                        .scores(teams.stream().map(team -> Score.builder()
                                .team(team)
                                .score(null)
                                .build())
                                .collect(toList()))
                        .build())
                        .collect(toSet()))
                .build());
    }

    public Optional<TimeTrail> getTimeTrail(String id) {
        return timeTrailRepository.findById(id);
    }

    public Optional<Score> updateScore(Long id, Long value) {
        return scoreRepository.findById(id)
                .map(score -> {
                    score.setScore(value);
                    return score;
                })
                .map(score -> scoreRepository.save(score));

    }

    public Team placeBet(String userID, Long stageID, Long teamID) {
        Team team = teamRepository.findById(teamID)
                .orElseThrow();
        User user = userRepository.findByUserCredentialsUsername(userID)
                .orElseThrow();


        timeTrailStageRepository.save(timeTrailStageRepository.findById(stageID).map(stage -> {
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


    public TimeTrialStagePrediction getPrediction(String username, Long stageID) {
        return timeTrailStageRepository.findById(stageID)
                .flatMap(stage -> stage.getPredictions()
                        .stream()
                        .filter(prediction -> prediction.getUser()
                                .getUserCredentials()
                                .getUsername()
                                .equals(username))
                        .findFirst())
                .orElseThrow();

    }
}
