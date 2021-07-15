package com.verhees.cm.controller;

import com.verhees.cm.model.request.CreateTimeTrailRequest;
import com.verhees.cm.model.response.TimeTrailResponse;
import com.verhees.cm.service.TimeTrialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/timetrail")
public class TimeTrialController {

    @Autowired
    private TimeTrialService timeTrialService;

    private final Logger logger = LoggerFactory.getLogger(TimeTrialController.class);

    @Secured("ADMIN")
    @PostMapping
    public ResponseEntity<?> createTimeTrail(@RequestBody CreateTimeTrailRequest request) {
        try {
            return ResponseEntity.ok(TimeTrailResponse.of(timeTrialService.createTimeTrail(request)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @Secured({"ADMIN", "ROLE_USER"})
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

    @Secured({"ROLE_USER"})
    @GetMapping("correctPredictions")
    public ResponseEntity<?> getMostCorrectPredictions(@RequestParam(name = "id") String id){
        try{
            return ResponseEntity.ok(timeTrialService.getMostCorrectPredictors(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }

    }
}
