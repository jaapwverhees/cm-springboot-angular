import {Component, Input, OnInit} from '@angular/core';
import {Game} from "../../../../models/competition/Game";
import {Score} from "../../../../models/competition/Score";
import {GameService} from "../../../../services/Game/game.service";
import {SetScoreRequest} from "../../../../models/requests/SetScoreRequest";

@Component({
  selector: 'app-manage-knockout-stage-game',
  templateUrl: './manage-knockout-stage-game.component.html',
  styleUrls: ['./manage-knockout-stage-game.component.scss']
})
export class ManageKnockoutStageGameComponent implements OnInit {

  @Input()
  game: Game;
  match: any;

  constructor(private gameService: GameService) { }

  ngOnInit() {

  }

  onFormSubmit() {

  }

  add(element: HTMLInputElement, score: Score) {
    let request = new SetScoreRequest()
    request.scoreID = score.id
    request.value = Number(element.value);
    this.gameService.setScore(request).subscribe(res =>{
      console.log(res);
    });
  }
}
