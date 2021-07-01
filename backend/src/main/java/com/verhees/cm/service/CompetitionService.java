package com.verhees.cm.service;

import com.verhees.cm.model.competition.Competition;
import com.verhees.cm.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    public List<Competition> findAll(){
        List<Competition> list = new ArrayList<>();
        competitionRepository.findAll().forEach(list::add);
        return list;
    }
}
