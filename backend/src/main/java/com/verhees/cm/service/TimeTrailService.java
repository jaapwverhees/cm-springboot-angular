package com.verhees.cm.service;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.repository.ScoreRepository;
import com.verhees.cm.repository.TeamRepository;
import com.verhees.cm.repository.TimeTrailRepository;
import com.verhees.cm.repository.TimeTrailStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class TimeTrailService {

    @Autowired
    private TimeTrailRepository timeTrailRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TimeTrailStageRepository timeTrailStageRepository;

    public TimeTrail createTimeTrail(CreateTimeTrailRequest request) {
        List<Team> teams = request.getTeams().stream()
                .map(team -> Team.builder()
                        .name(team)
                        .build())
                .map(teamRepository::save)
                .collect(toList());

        TimeTrail timeTrail = TimeTrail.builder()
                .name(request.getName())
                .stages(request.getStages()
                        .stream()
                        .map(stage -> TimeTrialStage.builder()
                                .date(stage)
                                .teams(teams)
//                                .scores(teams.stream().map(team -> Score.builder()
//                                        .team(team)
//                                        .build())
//                                        .collect(toList()))
                                .build())
//                        .map(timeTrailStageRepository::save)
                        .collect(toSet()))
                .build();

        System.out.println("hello");
        TimeTrail newTimeTrail = timeTrailRepository.save(timeTrail);
        return newTimeTrail;
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
}
