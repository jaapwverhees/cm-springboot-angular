package com.verhees.cm.controller;

import com.verhees.cm.model.exceptions.IllegalDateException;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.service.TimeTrialStageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestController()
@RequestMapping("api/timetrailStage")
public class TimeTrialStageController {

    @Autowired
    private TimeTrialStageService timeTrialStageService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrialController.class);

    @GetMapping("/bet")
    public ResponseEntity<?> bet(@RequestParam(name = "teamID") Long teamID, @RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(timeTrialStageService.placeBet(getPrincipal().getUsername(), stageID, teamID));
        } catch (IllegalDateException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getPrediction")
    public ResponseEntity<?> getPrediction(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(ofNullable(timeTrialStageService.getPrediction(getPrincipal().getUsername(), stageID))
                    .orElse(null));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getWinner")
    public ResponseEntity<?> getWinner(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(ofNullable(timeTrialStageService.getWinner(stageID))
                    .orElse(null));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    private UserDetails getPrincipal() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/getCorrectPredictions")
    public ResponseEntity<?> getCorrectPredictions(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(timeTrialStageService.getCorrectPredictions(stageID));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/setWinner")
    public ResponseEntity<?> setWinner(@RequestParam(name = "stageID") Long stageID, @RequestParam(name = "teamID") Long teamID) {
        try {
            return timeTrialStageService.setWinner(stageID, teamID)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
