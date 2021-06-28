package com.verhees.cm.timeTrail;

import com.verhees.cm.model.CreateTimeTrailResponse;
import com.verhees.cm.model.Team;
import com.verhees.cm.model.TimeTrialStage;
import com.verhees.cm.user.model.User;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("api/timetrail")
public class TimeTrailController {
    @PostMapping
    public CreateTimeTrailResponse createTimeTrail(@RequestBody CreateTimeTrailRequest request) {
        return CreateTimeTrailResponse.builder()
                .stages(asList(TimeTrialStage.builder()
                        .teams(asList(Team.builder()
                                .name("hello")
                                .build()))
                        .build()))
                .build();
    }
}
