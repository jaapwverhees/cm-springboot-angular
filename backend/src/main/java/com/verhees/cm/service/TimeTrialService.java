package com.verhees.cm.service;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.repository.TimeTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.Optional.ofNullable;
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

    public Set<String> getMostCorrectPredictors(String id) {
        List<TimeTrialStagePrediction> list = new ArrayList<>();
        timeTrailRepository.findById(id)
                .orElseThrow()
                .getStages()
                .forEach(stage -> list.addAll(stage.getPredictions()));

        return ofNullable(getPredictorsByValue(list))
                .map(map -> map.size() > 0 ? getKeys(map, Collections.max(map.values())) : new HashSet<String>())
                .orElse(emptySet());
    }

    private Map<String, Long> getPredictorsByValue(List<TimeTrialStagePrediction> predictions) {
        return predictions.stream()
                .filter(prediction -> prediction.getTeam()
                        .equals(prediction.getStage().getWinner()))
                .map(TimeTrialStagePrediction::getUser)
                .map(User::getUserCredentials)
                .map(UserCredentials::getUsername)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public <K, V> Set<K> getKeys(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
