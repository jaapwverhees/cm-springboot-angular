import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {TimeTrial} from "../../models/competition/TimeTrial";
import {Team} from "../../models/competition/Team";
import {TimeTrialStage} from "../../models/competition/TimeTrialStage";
import {Score} from "../../models/competition/Score";
import {Stage} from "../../models/competition/Stage";

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
