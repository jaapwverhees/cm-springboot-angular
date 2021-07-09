package com.verhees.cm.model.response;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class TimeTrailResponse {
    private String name;
    private Set<TimeTrialStage> stages;

    public static TimeTrailResponse of(TimeTrail trial) {
        return TimeTrailResponse.builder()
                .name(trial.getName())
                .stages(trial.getStages())
                .build();
    }
}
