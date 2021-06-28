package com.verhees.cm.model.stage;

import com.verhees.cm.model.predictions.TimeTrialStagePrediction;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeTrialStage extends Stage {

    @OneToMany
    private List<Team> teams;

    @OneToMany
    private List<Score> scores;

    @OneToMany
    private List<TimeTrialStagePrediction> predictions;

}
