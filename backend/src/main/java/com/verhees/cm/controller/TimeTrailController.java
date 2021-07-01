package com.verhees.cm.controller;

import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.response.TimeTrailResponse;
import com.verhees.cm.model.response.TimeTrailStageResponse;
import com.verhees.cm.service.TimeTrailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/timetrail")
public class TimeTrailController {
    @Autowired
    private TimeTrailService timeTrailService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrailController.class);

    @PostMapping
    public ResponseEntity<?> createTimeTrail(@RequestBody CreateTimeTrailRequest request) {
        try {
            return ResponseEntity.ok(TimeTrailResponse.of(timeTrailService.createTimeTrail(request)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/score")
    public ResponseEntity<?> updateScore(@RequestParam(name = "id") Long id, @RequestParam(name = "value") Long value) {
        try {
            return timeTrailService.updateScore(id, value)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getTimeTrail(@RequestParam(name = "id") String id) {
        try {
            TimeTrail timeTrail = timeTrailService.getTimeTrail(id).orElse(new TimeTrail());
            timeTrail.getStages().forEach(timeTrialStage -> timeTrialStage.getTeams().forEach(team -> {
                System.out.println(team.getName());
            }));
            return timeTrailService.getTimeTrail(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
