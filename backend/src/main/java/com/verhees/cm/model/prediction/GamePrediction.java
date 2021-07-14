package com.verhees.cm.model.prediction;

import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "game_prediction")
public class GamePrediction extends Prediction{
    @OneToOne
    private Game game;

    @OneToOne
    private Team team;
}
