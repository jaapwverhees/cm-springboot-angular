package com.verhees.cm.controller;

import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.service.TournamentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
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
    public void createTournamentTest_validInput_status200() {
        when(tournamentService.createTournament(any()))
                .thenReturn(new Tournament());
        assertEquals(200, tournamentController.createTournament(new CreateTournamentRequest()).getStatusCode().value());
    }

    @Test
    public void createTournamentTest_InvalidInput_status500() {
        when(tournamentService.createTournament(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, tournamentController.createTournament(new CreateTournamentRequest()).getStatusCode().value());
    }

    @Test
    public void getTournamentTest_validInput_status200() {
        when(tournamentService.getTournament(any()))
                .thenReturn(new Tournament());
        assertEquals(200, tournamentController.getTournament("").getStatusCode().value());
    }

    @Test
    public void getTournamentTest_InvalidInput_status500() {
        when(tournamentService.getTournament(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, tournamentController.getTournament("").getStatusCode().value());
    }

    @Test
    public void getMostCorrectPredictionsTest_validInput_status200() {
        when(tournamentService.getMostCorrectPredictors(any()))
                .thenReturn(new HashSet<>());
        assertEquals(200, tournamentController.getMostCorrectPredictions("").getStatusCode().value());
    }

    @Test
    public void getMostCorrectPredictionsTest_InvalidInput_status500() {
        when(tournamentService.getMostCorrectPredictors(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, tournamentController.getMostCorrectPredictions("").getStatusCode().value());
    }
}
