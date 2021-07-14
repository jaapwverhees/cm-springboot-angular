package com.verhees.cm.model.response;

import com.verhees.cm.model.exceptions.DrawException;
import com.verhees.cm.model.exceptions.NoScoreException;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {
    private Long id;

    private Score teamOne;

    private Score teamTwo;

    @Builder.Default
    private String status = "NOT_PLAYED";


    private Team winner;

    public static GameResponse of (Game game){
        String status = "PLAYED";
        Team winner = null;
        try{
            winner = game.calculateWinner();
        } catch (NoScoreException e){
            status = "NO_SCORES";
        } catch (DrawException e){
            status = "DRAW";
        }
        return GameResponse.builder()
                .id(game.getId())
                .status(status)
                .winner(winner)
                .build();
    }
}
