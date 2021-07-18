import { Component, OnInit } from '@angular/core';
import {KnockoutService} from "../../services/knockout/knockout.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Knockout} from "../../models/competition/Knockout";
import {KnockoutStage} from "../../models/competition/KnockoutStage";
import {Team} from "../../models/competition/Team";

@Component({
  selector: 'app-bet-knock-out',
  templateUrl: './bet-knock-out.component.html',
  styleUrls: ['./bet-knock-out.component.scss']
})
export class BetKnockOutComponent implements OnInit {

  knockout: Knockout;
  stages: KnockoutStage[];
  teams: Team[];
  correctPredictors: any;
  disabled: boolean;
  constructor(private knockoutService: KnockoutService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.knockoutService.getKnockout(params['id']).subscribe(result => {
        this.knockout = result;
        this.stages = result.stages;
        this.disabled = new Date(this.knockout.maxDate) <= new Date();
        this.knockoutService.getMostCorrectPredictions(this.knockout.name).subscribe(result => {
          this.correctPredictors = result;
        });
      });
    });
  }
}
