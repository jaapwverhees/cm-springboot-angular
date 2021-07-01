package com.verhees.cm.model.stage;

import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class TimeTrialStage extends Stage {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeTrialStagePrediction> predictions;

}
