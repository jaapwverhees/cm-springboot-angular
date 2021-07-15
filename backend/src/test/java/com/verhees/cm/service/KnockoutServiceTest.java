package com.verhees.cm.service;

import com.verhees.cm.model.competition.Knockout;
import com.verhees.cm.model.request.CreateKnockoutRequest;
import com.verhees.cm.model.request.GameRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class KnockoutServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        Knockout knockout = new KnockoutService().create(CreateKnockoutRequest.builder()
                .matches(new ArrayList<GameRequest>() {
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
                    }
                })
                .build());
        System.out.println();
    }

}
