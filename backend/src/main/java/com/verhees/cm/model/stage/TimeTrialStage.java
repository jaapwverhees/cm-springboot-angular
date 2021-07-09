package com.verhees.cm.model.stage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "time_trial_stage")
public class TimeTrialStage extends Stage {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_stage",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams = new ArrayList<>();

    @Deprecated
    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    @Builder.Default
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeTrialStagePrediction> predictions = new ArrayList<>();

    @OneToOne
    private Team Winner;

}
