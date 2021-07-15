import {Component, Input, OnInit} from '@angular/core';
import {Game} from "../../../models/competition/Game";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {GameService} from "../../../services/Game/game.service";
import {Team} from "../../../models/competition/Team";
import {Score} from "../../../models/competition/Score";
import {DateUtil} from "../../../util/date/date-util";

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.scss']
})
export class GamesComponent implements OnInit {

  @Input()
  game: Game;
  @Input()
  scores: Score[];
  winner: any;
  @Input()
  disabled: boolean;
  gameForm: FormGroup;
  myTeam: String;
  correctPredictions: string[] = [];
  constructor(private fb: FormBuilder,
              private gameService: GameService) {
  }

  ngOnInit() {
    this.gameForm = this.fb.group({
      team: new FormControl({value: [null]})
    });

    this.gameService.getCorrectPredictions(this.game.id).subscribe(res => {
      if (res.length > 0) {
        this.correctPredictions = res;
      }
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
  }

  onChanges(): void {
    this.gameForm.valueChanges.subscribe(val => {
      this.scores.forEach(score => {
        if(score.team.name === val.team){
          this.gameService.BetGame(this.game.id, score.team.id).subscribe(name => {
          });
        }
      });
    });
  }
}
