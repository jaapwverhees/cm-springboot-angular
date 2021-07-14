package com.verhees.cm.controller;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.service.TimeTrialService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static java.util.Optional.of;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTrialControllerTest {

    @Mock
    private TimeTrialService service;

    @InjectMocks
    private TimeTrialController controller;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void createTimeTrail() {
        when(service.createTimeTrail(any()))
                .thenReturn(new TimeTrail());
        assertEquals(200, controller.createTimeTrail(new CreateTimeTrailRequest()).getStatusCodeValue());
    }

    @Test
    public void createTimeTrailThrowsException() {
        when(service.createTimeTrail(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.createTimeTrail(new CreateTimeTrailRequest()).getStatusCodeValue());
    }

    @Test
    public void getTimeTrail() {
        when(service.getTimeTrail(any()))
                .thenReturn(of(new TimeTrail()));
        assertEquals(200, controller.getTimeTrail("").getStatusCodeValue());
    }

    @Test
    public void getTimeTrailThrowsException() {
        when(service.getTimeTrail(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.getTimeTrail("").getStatusCodeValue());
    }

    @Test
    public void getMostCorrectPredictions() {
        when(service.getMostCorrectPredictors(any()))
                .thenReturn(new HashSet<>());
        assertEquals(200, controller.getMostCorrectPredictions("").getStatusCodeValue());
    }

    @Test
    public void getMostCorrectPredictionsThrowsException() {
        when(service.getMostCorrectPredictors(any()))
                .thenThrow(new RuntimeException());
        assertEquals(500, controller.getMostCorrectPredictions("").getStatusCodeValue());
    }
}
