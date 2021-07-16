import {Component, Input, OnInit} from '@angular/core';
import {Game} from "../../../../models/competition/Game";
import {Knockout} from "../../../../models/competition/Knockout";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Team} from "../../../../models/competition/Team";
import {KnockoutService} from "../../../../services/knockout/knockout.service";
import {GameService} from "../../../../services/Game/game.service";

@Component({
  selector: 'app-bet-knockout-game',
  templateUrl: './bet-knockout-game.component.html',
  styleUrls: ['./bet-knockout-game.component.scss']
})
export class BetKnockoutGameComponent implements OnInit {

  @Input()
  game: Game;
  @Input()
  knockout: Knockout;
  @Input()
  counter: number;
  disabled = false;
  winner: any;
  gameForm: FormGroup;
  teams: Team[];
  correctPredictions: string[];
  myTeam: string;

  constructor(private fb: FormBuilder,
              private gameService: GameService) {
  }

  ngOnInit() {
    this.teams = []
    this.correctPredictions = [];
    this.knockout
      .stages
      .sort((a, b) => a.stageIndex - b.stageIndex)[0]
      .games
      .forEach(game => {
        this.teams.push(game.teamOne.team);
        this.teams.push(game.teamTwo.team);

        this.gameForm = this.fb.group({
          team: new FormControl({value: [null], disabled: new Date(this.knockout.maxDate) > new Date()})
        });
      });
    this.gameService.getPrediction(this.game.id).subscribe(val => {
      if(val){
        this.myTeam = val.team.name;
        this.gameForm.patchValue({
          team: val.team.name
        });
      }
      this.onChanges();
    });
    this.gameService.getCorrectPredictions(this.game.id).subscribe(res => {
      if (res.length > 0) {
        this.correctPredictions = res;
      }
    });
  }

  onChanges(): void {
    this.gameForm.valueChanges.subscribe(val => {
      this.teams.forEach(team => {
        if(team.name === val.team){
          this.gameService.BetGame(this.game.id, team.id).subscribe(name => {
          });
        }
      });
    });
  }
}
