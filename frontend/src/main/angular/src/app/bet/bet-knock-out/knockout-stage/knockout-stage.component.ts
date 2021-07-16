import {Component, Input, OnInit} from '@angular/core';
import {KnockoutStage} from "../../../models/competition/KnockoutStage";
import {Team} from "../../../models/competition/Team";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Knockout} from "../../../models/competition/Knockout";
import {Game} from "../../../models/competition/Game";

@Component({
  selector: 'app-knockout-stage',
  templateUrl: './knockout-stage.component.html',
  styleUrls: ['./knockout-stage.component.scss']
})
export class KnockoutStageComponent implements OnInit {

  @Input()
  stage: KnockoutStage;
  @Input()
  knockout: Knockout;
  teams: Team[];
  winner: any;
  counter = 0;
  games: Game[];
  teamForm: FormGroup;
  disabled: boolean;
  correctPredictions = [];

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.teams = [];
    this.knockout.stages.sort((a, b) => a.stageIndex - b.stageIndex)[0].games
      .forEach(game => {
        this.teams.push(game.teamOne.team)
        this.teams.push(game.teamTwo.team)
      })
    this.games = this.stage.games;
    this.teamForm = this.fb.group({
      team: new FormControl({value: [null], disabled: this.disabled})
    });
  }

}
