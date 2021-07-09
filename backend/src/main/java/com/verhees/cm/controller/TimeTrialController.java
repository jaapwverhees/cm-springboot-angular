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

import static java.util.Optional.ofNullable;

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

    @GetMapping("/setWinner")
    public ResponseEntity<?> setWinner(@RequestParam(name = "stageID") Long stageID, @RequestParam(name = "teamID") Long teamID) {
        try {
            return timeTrialService.setWinner(stageID, teamID)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getWinner")
    public ResponseEntity<?> getWinner(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(ofNullable(timeTrialService.getWinner(stageID))
                    .orElse(null));
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
            return ResponseEntity.ok(ofNullable(timeTrialService.getPrediction(getPrincipal().getUsername(), stageID))
                    .orElse(null));
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

    @GetMapping("/getCorrectPredictions")
    public ResponseEntity<?> getCorrectPredictions(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(timeTrialService.getCorrectPredictions(stageID));
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
