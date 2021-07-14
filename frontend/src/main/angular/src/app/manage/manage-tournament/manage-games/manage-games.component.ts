import {Component, Input, OnInit} from '@angular/core';
import {Game} from "../../../models/competition/Game";
import {Stage} from "../../../models/competition/Stage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TimeTrailService} from "../../../services/timetrail/time-trail.service";
import {TimeTrailStageService} from "../../../services/timetrailstage/time-trail-stage.service";
import {DateUtil} from "../../../util/date/date-util";
import {Score} from "../../../models/competition/Score";
import {TournamentService} from "../../../services/tournament/tournament.service";
import {CreateChampionShipRequest} from "../../../models/requests/CreateChampionShipRequest";
import {SetScoreRequest} from "../../../models/requests/SetScoreRequest";
import {GameService} from "../../../services/Game/game.service";

@Component({
  selector: 'app-manage-games',
  templateUrl: './manage-games.component.html',
  styleUrls: ['./manage-games.component.scss']
})
export class ManageGamesComponent implements OnInit {

  @Input()
  game?: Game;

  disabled: false;

  constructor(private fb: FormBuilder,
              private gameService: GameService) {
  }

  ngOnInit() {
    //this.disabled = DateUtil.currentDateAfterStage(new Date(this.game.date));
  }

  add(element: HTMLInputElement, score: Score) {
    score.score = Number(element.value);
    let request = new SetScoreRequest();
    request.value = Number(element.value);
    request.scoreID = score.id;
    this.gameService.setScore(request).subscribe(val => {
      score = val;
    });
  }
}
