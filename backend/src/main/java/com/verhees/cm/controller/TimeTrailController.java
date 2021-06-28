package com.verhees.cm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timetrail")
public class TimeTrailController {

    @PostMapping
    public ResponseEntity<?> createTimeTrail(){
        System.out.println("hello");
        return ResponseEntity.ok("hello");
    }
}
