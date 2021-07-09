package com.verhees.cm.service;

import com.verhees.cm.model.competition.Competition;
import com.verhees.cm.repository.TimeTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private TimeTrailRepository timeTrailRepository;

    public List<Competition> findAll() {
        List<Competition> list = new ArrayList<>();
        timeTrailRepository.findAll().forEach(list::add);
        return list;
    }
}
