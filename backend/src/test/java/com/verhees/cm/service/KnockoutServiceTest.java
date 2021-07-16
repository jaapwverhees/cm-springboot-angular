package com.verhees.cm.service;

import com.verhees.cm.model.competition.Knockout;
import com.verhees.cm.model.request.CreateKnockoutRequest;
import com.verhees.cm.model.request.GameRequest;
import com.verhees.cm.repository.KnockoutRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class KnockoutServiceTest {

    @Mock
    private KnockoutRepository knockoutRepository;

    @InjectMocks
    private KnockoutService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void create() {
        when(knockoutRepository.save(any()))
                .then(returnsFirstArg());

        assertEquals(4, service.create(CreateKnockoutRequest.builder()
                .matches(new ArrayList<>() {
                    {
                        add(GameRequest.builder()
                                .teamOne("teamOne")
                                .teamTwo("teamTwo")
                                .build());
                        add(GameRequest.builder()
                                .teamOne("teamOne")
                                .teamTwo("teamTwo")
                                .build());
                        add(GameRequest.builder()
                                .teamOne("teamOne")
                                .teamTwo("teamTwo")
                                .build());
                        add(GameRequest.builder()
                                .teamOne("teamOne")
                                .teamTwo("teamTwo")
                                .build());
                        add(GameRequest.builder()
                                .teamOne("teamOne")
                                .teamTwo("teamTwo")
                                .build());
                    }})
                .build())
                .getStages()
                .size());
    }

}
