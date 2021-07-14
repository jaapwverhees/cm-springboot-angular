package com.verhees.cm.controller;

import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.service.TournamentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TournamentControllerTest {

    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentController tournamentController;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void createTournament() {
        when(tournamentService.createTournament(any()))
                .thenReturn(new Tournament());
    }

    @Test
    public void getTournament() {
    }

    @Test
    public void setScore() {
    }
}
