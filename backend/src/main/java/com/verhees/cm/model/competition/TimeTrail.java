package com.verhees.cm.model.competition;

import com.verhees.cm.model.competition.Competition;
import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class TimeTrail extends Competition {
    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeTrialStage> stages;
}
