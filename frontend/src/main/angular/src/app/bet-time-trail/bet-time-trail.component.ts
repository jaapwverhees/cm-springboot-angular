import { Component, OnInit } from '@angular/core';
import {TimeTrail} from "../models/TimeTrail";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {MatSelectModule} from '@angular/material/select';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-bet-time-trail',
  templateUrl: './bet-time-trail.component.html',
  styleUrls: ['./bet-time-trail.component.scss']
})
export class BetTimeTrailComponent implements OnInit {

  timeTrail: TimeTrail;



  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private timeTrailService: TimeTrailService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.timeTrailService.getCompetition(params['id']).subscribe(result => {
        this.timeTrail = result;
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

  setpredition(logo: HTMLInputElement, id: number) {

  }

  getPredition(id: number): number {
    return 0;
  }
}
