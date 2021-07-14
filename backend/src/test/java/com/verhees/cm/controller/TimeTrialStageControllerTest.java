package com.verhees.cm.controller;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.service.TimeTrialService;
import com.verhees.cm.service.TimeTrialStageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;

import static java.util.Optional.of;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTrialStageControllerTest {

    @Mock
    private TimeTrialStageService service;

    @InjectMocks
    private TimeTrialStageController controller;

    @Before
    public void setup() {
        initMocks(this);
    }


    @Test
    public void betThrowsException() {
        when(service.placeBet(any(), any(), any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.bet(1L, 1L).getStatusCodeValue());
    }

    @Test
    public void getPredictionThrowsException() {
        when(service.getPrediction(any(), any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.bet(1L, 1L).getStatusCodeValue());
    }

    @Test
    public void getCorrectPredictions() {
        when(service.getCorrectPredictions(any()))
                .thenReturn(new ArrayList<>());
        assertEquals(200, controller.getCorrectPredictions(1L).getStatusCodeValue());
    }

    @Test
    public void getCorrectPredictionsException() {
        when(service.getCorrectPredictions(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.getCorrectPredictions(1L).getStatusCodeValue());
    }

    @Test
    public void setWinnerThrowsException() {
        when(service.setWinner(any(), any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.setWinner(1L, 1L).getStatusCodeValue());
    }
}
