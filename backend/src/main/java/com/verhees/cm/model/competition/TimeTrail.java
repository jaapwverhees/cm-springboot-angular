package com.verhees.cm.model.competition;

import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class TimeTrail extends Competition {
    @OneToMany(mappedBy = "competitie")
    private Set<TimeTrialStage> stages;
}
