package com.verhees.cm.model.competition;

import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTrail extends Competition {
    @OneToMany
    private List<TimeTrialStage> stages;
}
