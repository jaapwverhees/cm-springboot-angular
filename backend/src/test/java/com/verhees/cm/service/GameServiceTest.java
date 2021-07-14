package com.verhees.cm.service;

import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GameService service;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void placeBet() {
        Team team = Team.builder()
                .name("TEAM")
                .build();
        when(teamRepository.findById(anyLong()))
                .thenReturn(ofNullable(team));
        when(userRepository.findByUserCredentialsUsername(any()))
                .thenReturn(of(new User(new UserCredentials("name", "password", ""))));
        when(gameRepository.save(any()))
                .thenReturn(new Game());
        when(gameRepository.findById(any()))
                .thenReturn(of(Game.builder()
                        .predictions(new ArrayList<>())
                        .build()));
        assertEquals("TEAM", service.placeBet("", 1L, 1L).getName());
    }
}
