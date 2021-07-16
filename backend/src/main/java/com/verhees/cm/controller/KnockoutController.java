package com.verhees.cm.controller;

import com.verhees.cm.model.request.CreateKnockoutRequest;
import com.verhees.cm.model.request.CreateTournamentRequest;
import com.verhees.cm.service.KnockoutService;
import com.verhees.cm.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/knockout")
public class KnockoutController {

    @Autowired
    private KnockoutService service;

    private final Logger logger = LoggerFactory.getLogger(KnockoutController.class);

    @Secured({"ADMIN"})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateKnockoutRequest request) {
        try {
            return ResponseEntity.ok(service.create(request));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured({"ADMIN", "ROLE_USER"})
    @GetMapping()
    public ResponseEntity<?> get(@RequestParam(name = "id") String id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured({"ADMIN"})
    @GetMapping("generate")
    public ResponseEntity<?> generateNextStage(@RequestParam(name = "knockoutID") String knockoutID, @RequestParam(name = "stageIndex") int stageIndex) {
        try {
            return ResponseEntity.ok(service.generateNextStage(stageIndex, knockoutID));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping("correctPredictions")
    public ResponseEntity<?> getMostCorrectPredictions(@RequestParam(name = "id") String id){
        try{
            return ResponseEntity.ok(service.getMostCorrectPredictors(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
