import { Component, OnInit } from '@angular/core';
import {TimeTrial} from "../../models/competition/TimeTrial";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {MatSelectModule} from '@angular/material/select';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Stage} from "../../models/competition/Stage";
import {TimeTrailStageService} from "../../services/timetrailstage/time-trail-stage.service";

@Component({
  selector: 'app-bet-time-trail',
  templateUrl: './bet-time-trail.component.html',
  styleUrls: ['./bet-time-trail.component.scss']
})
export class BetTimeTrailComponent implements OnInit {

  timeTrail: TimeTrial;
  stages: Stage[];
  correctPredictors: string[];

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private timeTrailService: TimeTrailService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.timeTrailService.getCompetition(params['id']).subscribe(result => {
        this.timeTrail = result;
        this.stages = this.timeTrail.stages;
        this.timeTrailService.getMostCorrectPredictions(this.timeTrail.name).subscribe(result => {
          this.correctPredictors = result;
        });
      })
    });
  }
}
