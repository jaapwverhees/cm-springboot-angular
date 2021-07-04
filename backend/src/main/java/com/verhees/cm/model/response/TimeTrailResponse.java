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

    public static TimeTrailResponse of(TimeTrail trail) {
        return TimeTrailResponse.builder()
                .name(trail.getName())
                .stages(trail.getStages())
                .build();
    }
}
