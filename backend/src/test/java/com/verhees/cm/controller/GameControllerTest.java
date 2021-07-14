package com.verhees.cm.controller;

import com.verhees.cm.model.request.SetScoreRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.service.GameService;
import com.verhees.cm.service.TournamentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getCorrectPredictions() {
        when(gameService.getCorrectPredictions(any()))
                .thenReturn(emptyList());
        assertEquals(200, gameController.getCorrectPredictions(1L).getStatusCodeValue());
    }

    @Test
    public void getCorrectPredictionsThrowsException() {
        when(gameService.getCorrectPredictions(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, gameController.getCorrectPredictions(1L).getStatusCodeValue());
    }

    @Test
    public void setScorePredictions() {
        when(gameService.setScore(any()))
                .thenReturn(new Score());
        assertEquals(200, gameController.setScore(new SetScoreRequest()).getStatusCodeValue());
    }

    @Test
    public void setScoreThrowsException() {
        when(gameService.setScore(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, gameController.setScore(new SetScoreRequest()).getStatusCodeValue());
    }
}
