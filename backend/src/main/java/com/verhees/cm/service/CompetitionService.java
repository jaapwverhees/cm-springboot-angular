package com.verhees.cm.service;

import com.verhees.cm.model.competition.Competition;
import com.verhees.cm.model.response.CompetitionResponse;
import com.verhees.cm.repository.KnockoutRepository;
import com.verhees.cm.repository.TimeTrailRepository;
import com.verhees.cm.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private TimeTrailRepository timeTrailRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private KnockoutRepository knockoutRepository;

    public List<CompetitionResponse> findAll() {
        List<CompetitionResponse> list = new ArrayList<>();
        timeTrailRepository.findAll().forEach(val -> {
            list.add(CompetitionResponse.of(val));
        });
        tournamentRepository.findAll().forEach(val -> {
            list.add(CompetitionResponse.of(val));
        });
        knockoutRepository.findAll().forEach(val -> {
            list.add(CompetitionResponse.of(val));
        });
        return list;
    }
}
