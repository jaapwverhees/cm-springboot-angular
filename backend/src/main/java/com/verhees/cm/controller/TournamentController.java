package com.verhees.cm.controller;

import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrialController.class);

    @PostMapping
    public ResponseEntity<?> createTournament(@RequestBody CreateTournamentRequest request) {
        try {
            return ResponseEntity.ok(tournamentService.createTournament(request));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getTournament(@RequestParam(name = "id") String id) {
        try {
            return ResponseEntity.ok(tournamentService.getTournament(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("correctPredictions")
    public ResponseEntity<?> getMostCorrectPredictions(@RequestParam(name = "id") String id){
        try{
            return ResponseEntity.ok(tournamentService.getMostCorrectPredictors(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }

    }
}
