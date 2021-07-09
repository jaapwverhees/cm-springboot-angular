package com.verhees.cm.controller;

import com.verhees.cm.service.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrialController.class);

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(competitionService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
