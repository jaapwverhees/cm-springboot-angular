package com.verhees.cm.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verhees.cm.model.exceptions.DrawException;
import com.verhees.cm.model.exceptions.NoScoreException;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.request.GameRequest;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Score teamOne;

    @OneToOne(cascade = CascadeType.ALL)
    private Score teamTwo;

    @Builder.Default
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<GamePrediction> predictions = new ArrayList<>();

    private String Winner;

    private int gameIndex;

    public Team calculateWinner(){
        if(teamOne.getScore() == null || teamTwo.getScore() == null) {
            throw new NoScoreException();
        }
        if(teamOne.getScore().equals(teamTwo.getScore())) {
            throw new DrawException();
        }
        return teamOne.getScore() > teamTwo.getScore() ?
                teamOne.getTeam() : teamTwo.getTeam();
    }

    public List<String> correctPredictions(){
        try{
            Team team = calculateWinner();
            return predictions.stream()
                    .filter(prediction -> prediction.getTeam().equals(team))
                    .map(prediction -> prediction.getUser().getUserCredentials().getUsername())
                    .collect(toList());
        } catch(DrawException | NoScoreException e){
            return emptyList();
        }
    }

    public static Game of(GameRequest gameRequest, int index) {
        return Game.builder()
                .teamOne(Score.builder()
                        .team(Team.builder()
                                .name(gameRequest.getTeamOne())
                                .build())
                        .build())
                .teamTwo(Score.builder()
                        .team(Team.builder()
                                .name(gameRequest.getTeamTwo())
                                .build())
                        .build())
                .gameIndex(index)
                .build();
    }
}
