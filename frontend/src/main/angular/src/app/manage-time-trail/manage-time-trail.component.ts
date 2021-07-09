import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {TimeTrail} from "../models/TimeTrail";
import {Team} from "../models/Team";
import {TimeTrailStage} from "../models/TimeTrailStage";
import {Score} from "../models/Score";
import {Stage} from "../models/Stage";

@Component({
  selector: 'app-manage-time-trail',
  templateUrl: './manage-time-trail.component.html',
  styleUrls: ['./manage-time-trail.component.scss']
})
export class ManageTimeTrailComponent implements OnInit {

  timeTrail: TimeTrail;
  stages: Stage[];

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private timeTrailService: TimeTrailService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.timeTrailService.getCompetition(params['id']).subscribe(result => {
        this.timeTrail = result;
        this.stages = result.stages;
        console.log(result);
      })
    });
  }

  setScore(logo: HTMLInputElement, id: number) {
    //TODO when time implement TimeTrailScore;
    this.timeTrailService.setScore(logo.value, id).subscribe(result => {
      this.timeTrail.stages.forEach(stage => {
        stage.scores.forEach(score =>{
          if(score.id === result.id){
            score = result;
          }
        })
      })
    })
  }

}
