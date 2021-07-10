import { Component, OnInit } from '@angular/core';
import {TimeTrail} from "../models/TimeTrail";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {MatSelectModule} from '@angular/material/select';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Stage} from "../models/Stage";
import {TimeTrailStageService} from "../services/timetrailstage/time-trail-stage.service";

@Component({
  selector: 'app-bet-time-trail',
  templateUrl: './bet-time-trail.component.html',
  styleUrls: ['./bet-time-trail.component.scss']
})
export class BetTimeTrailComponent implements OnInit {

  timeTrail: TimeTrail;
  stages: Stage[]



  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private timeTrailService: TimeTrailService,
              private timeTrialStageService: TimeTrailStageService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.timeTrailService.getCompetition(params['id']).subscribe(result => {
        this.timeTrail = result;
        this.stages = this.timeTrail.stages;
      })
    });
  }
}
