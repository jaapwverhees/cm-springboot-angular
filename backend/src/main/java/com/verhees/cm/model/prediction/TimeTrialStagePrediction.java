package com.verhees.cm.model.prediction;

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
@Table(name = "time_trial_stage_prediction")
public class TimeTrialStagePrediction extends Prediction {
    @OneToOne
    private TimeTrialStage stage;
    @OneToOne
    private Team team;
}
