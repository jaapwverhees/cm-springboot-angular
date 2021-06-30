package com.verhees.cm.service;

import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.repository.ScoreRepository;
import com.verhees.cm.repository.TeamRepository;
import com.verhees.cm.repository.TimeTrailRepository;
import com.verhees.cm.repository.TimeTrailStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TimeTrailService {

    @Autowired
    private TimeTrailRepository timeTrailRepository;

    @Autowired
    private TimeTrailStageRepository timeTrailStageRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeamRepository teamRepository;

    public TimeTrail createTimeTrail(CreateTimeTrailRequest request) {
        request.getTeams().forEach(team -> teamRepository.save(Team.builder()
                .name(team)
                .build()));
        TimeTrail timeTrail = timeTrailRepository.save(TimeTrail.builder()
                .name(request.getName())
                .stages(createStages(request))
                .build());
        return timeTrail;
    }

    private List<TimeTrialStage> createStages(CreateTimeTrailRequest request) {
        List<TimeTrialStage> stages = new ArrayList<>();

        List<Team> teams = request.getTeams()
                .stream()
                .map(team -> teamRepository.findTeamByName(team))
                .collect(toList());

        request.getStages()
                .forEach(date -> stages.add(TimeTrialStage.builder()
                .date(date)
                .teams(teams)
                .scores(createScores(request, teams))
                .build()));
        return stages;
    }

    private List<Team> createTeams(CreateTimeTrailRequest request) {
        return request.getTeams()
                .stream()
                .map(teamRequest -> Team.builder()
                        .name(teamRequest)
                        .build())
                .collect(toList());
    }

    private List<Score> createScores(CreateTimeTrailRequest request, List<Team> teams) {
        return teams.stream()
                .map(team -> Score.builder()
                        .team(team)
                        .build())
                .collect(toList());
    }

    public Optional<TimeTrail> getTimeTrail(String id) {
        return timeTrailRepository.findById(id);
    }

    public Optional<Score> updateScore(Long id, Long value) {
        return scoreRepository.findById(id)
                .map(score -> {
                    score.setScore(value);
                    return score; })
                .map(score -> scoreRepository.save(score));

    }
}
