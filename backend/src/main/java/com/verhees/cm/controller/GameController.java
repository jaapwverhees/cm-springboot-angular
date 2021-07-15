package com.verhees.cm.controller;

import com.verhees.cm.model.exceptions.IllegalDateException;
import com.verhees.cm.model.request.SetScoreRequest;
import com.verhees.cm.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestController
@RequestMapping("api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Secured("ROLE_USER")
    @GetMapping("/bet")
    public ResponseEntity<?> bet(@RequestParam(name = "teamID") Long teamID, @RequestParam(name = "gameID") Long stageID) {
        try {
            return ResponseEntity.ok(gameService.placeBet(getPrincipal().getUsername(), stageID, teamID));
        } catch (IllegalDateException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/getPrediction")
    public ResponseEntity<?> getPrediction(@RequestParam(name = "gameID") Long gameID) {
        try {
            return ResponseEntity.ok(ofNullable(gameService.getPrediction(getPrincipal().getUsername(), gameID))
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

    @Secured("ROLE_USER")
    @GetMapping("/getCorrectPredictions")
    public ResponseEntity<?> getCorrectPredictions(@RequestParam(name = "stageID") Long stageID) {
        try {
            return ResponseEntity.ok(gameService.getCorrectPredictions(stageID));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured("ADMIN")
    @PostMapping("score")
    public ResponseEntity<?> setScore(@RequestBody SetScoreRequest request) {
        try {
            return ResponseEntity.ok(gameService.setScore(request));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
