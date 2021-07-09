package com.verhees.cm.controller;

import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.response.TimeTrailResponse;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.service.TimeTrialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/timetrail")
public class TimeTrialController {
    @Autowired
    private TimeTrialService timeTrialService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrialController.class);

    @PostMapping
    public ResponseEntity<?> createTimeTrail(@RequestBody CreateTimeTrailRequest request) {
        try {
            return ResponseEntity.ok(TimeTrailResponse.of(timeTrialService.createTimeTrail(request)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/score")
    public ResponseEntity<?> updateScore(@RequestParam(name = "id") Long id, @RequestParam(name = "value") Long value) {
        try {
            return timeTrialService.updateScore(id, value)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/bet")
    public ResponseEntity<?> bet(@RequestParam(name = "teamID") Long teamID, @RequestParam(name = "stageID") Long stageID) {
        try {
            Team team = timeTrialService.placeBet(getPrincipal().getUsername(), stageID, teamID);
            return ResponseEntity.ok(team);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getPrediction")
    public ResponseEntity<?> getPrediction(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(timeTrialService.getPrediction(getPrincipal().getUsername(), stageID));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getTimeTrail(@RequestParam(name = "id") String id) {
        try {
            return timeTrialService.getTimeTrail(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    private UserDetails getPrincipal() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
