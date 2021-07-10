package com.verhees.cm.service;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.repository.TimeTrailRepository;
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

}
