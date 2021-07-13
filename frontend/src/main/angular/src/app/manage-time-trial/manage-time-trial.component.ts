import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {TimeTrial} from "../models/TimeTrial";
import {Team} from "../models/Team";
import {TimeTrialStage} from "../models/TimeTrialStage";
import {Score} from "../models/Score";
import {Stage} from "../models/Stage";

@Component({
  selector: 'app-manage-time-trail',
  templateUrl: './manage-time-trial.component.html',
  styleUrls: ['./manage-time-trial.component.scss']
})
export class ManageTimeTrialComponent implements OnInit {

  timeTrail: TimeTrial;
  stages: Stage[];

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private timeTrailService: TimeTrailService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.timeTrailService.getCompetition(params['id']).subscribe(result => {
        this.timeTrail = result;
        this.stages = result.stages;
      })
    });
  }
}
