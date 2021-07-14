package com.verhees.cm.service;

import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.repository.TournamentRepository;
import com.verhees.cm.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TournamentServiceTest {
    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void generateGamesTest_fourTeams_sixCombinations(){
        Set<Set<Team>> games = tournamentService.generateGames(Set.of(Team.builder()
                        .name("ONE")
                        .build(),
                Team.builder()
                        .name("TWO")
                        .build(),
                Team.builder()
                        .name("THREE")
                        .build(),
                Team.builder()
                        .name("FOUR")
                        .build()));
        assertEquals(6, games.size());
    }

    @Test
    public void createTournamentTest(){
        when(tournamentRepository.save(any())).then(returnsFirstArg());
        Tournament tournament = tournamentService.createTournament(CreateTournamentRequest.builder()
                .name("name")
                .teams(Set.of("teamOne", "teamTwo", "teamThree", "teamFour"))
                .build());

        assertEquals("name", tournament.getName());
        assertEquals(6, tournament.getGames().size());
    }


    @Test
    public void getTournament() {
        when(tournamentRepository.findById(""))
                .thenReturn(of(new Tournament()));
        assertNotNull(tournamentService.getTournament(""));
    }

    @Test
    public void getTournament_throwException() {
        boolean bool = false;
        when(tournamentRepository.findById(""))
                .thenReturn(Optional.empty());
        try {
            tournamentService.getTournament("");
        } catch(NoSuchElementException e){
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void getMostCorrectPredictors() {
        Team team = Team.builder()
                .name("winner")
                .build();
        Game game = Game.builder()
                .teamOne(Score.builder()
                        .team(team)
                        .score(5L)
                        .build())
                .teamTwo(Score.builder()
                        .team(new Team())
                        .score(3L)
                        .build())
                .predictions(singletonList(GamePrediction.builder()
                        .team(team)
                        .user(new User(new UserCredentials("name", "password", "ADMIN")))
                        .build()))
                .id(1L)
                .build();
        Tournament tournament = Tournament.builder()
                .name("name")
                .games(Set.of(game))
                .build();
        when(tournamentRepository.findById(""))
                .thenReturn(of(tournament));
        tournament.getGames().forEach(g ->{
            g.getPredictions().forEach(gamePrediction -> {
                gamePrediction.setGame(g);
            });
        });
        assertEquals(1, tournamentService.getMostCorrectPredictors("").size());
    }

    @Test
    public void calculateWinner() {
    }

    @Test
    public void getKeys() {
    }
}
