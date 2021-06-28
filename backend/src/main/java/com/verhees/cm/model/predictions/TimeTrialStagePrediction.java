package com.verhees.cm.model.predictions;

import com.verhees.cm.model.stage.TimeTrialStage;
import com.verhees.cm.model.team.Team;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TimeTrialStagePrediction extends Prediction {
    @OneToOne
    private TimeTrialStage stage;
    @OneToOne
    private Team team;
}
