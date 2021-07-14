package com.verhees.cm.controller;

import com.verhees.cm.model.competition.Competition;
import com.verhees.cm.model.response.CompetitionResponse;
import com.verhees.cm.service.CompetitionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionControllerTest {

    @Mock
    private CompetitionService competitionService;

    @InjectMocks
    private CompetitionController competitionController;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void findAllTest(){
        when(competitionService.findAll())
                .thenReturn(singletonList(new CompetitionResponse()));
        assertEquals(200, competitionController.findAll().getStatusCodeValue());
    }

    @Test
    public void findAllTestThrowsException(){
        when(competitionService.findAll())
                .thenThrow(new RuntimeException());
        assertEquals(500, competitionController.findAll().getStatusCodeValue());
    }
}
