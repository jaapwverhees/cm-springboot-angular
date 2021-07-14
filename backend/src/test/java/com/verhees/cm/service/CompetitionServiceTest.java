package com.verhees.cm.service;

import com.verhees.cm.controller.TimeTrialStageController;
import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.repository.TimeTrailRepository;
import com.verhees.cm.repository.TournamentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CompetitionServiceTest {

    @Mock
    private TimeTrailRepository timeTrailRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private CompetitionService competitionService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void findAll() {
        when(timeTrailRepository.findAll()).thenReturn(singletonList(TimeTrail.builder()
                .name("hello")
                .build()));
        when(tournamentRepository.findAll()).thenReturn(singletonList(Tournament.builder()
                .name("bla")
                .build()));
        assertEquals(2, competitionService.findAll().size());
    }
}
