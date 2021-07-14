package com.verhees.cm.service;

import com.verhees.cm.model.competition.Tournament;
import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.model.team.Team;
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

import java.util.Set;

import static org.junit.Assert.assertEquals;
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

        tournament.getName();
    }

}
