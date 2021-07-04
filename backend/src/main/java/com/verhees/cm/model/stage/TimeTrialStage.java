package com.verhees.cm.model.stage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.score.Score;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.prediction.TimeTrialStagePrediction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class TimeTrialStage extends Stage {

    @ManyToOne
    @JoinColumn(name="timetrail_id", nullable = false)
    private TimeTrail competitie;

    @ManyToMany
    @JoinTable(
            name = "team_stage",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Score> scores;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<TimeTrialStagePrediction> predictions;

}
