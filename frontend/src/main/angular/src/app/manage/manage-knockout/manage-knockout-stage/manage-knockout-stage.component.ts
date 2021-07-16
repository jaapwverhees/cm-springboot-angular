import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../../models/competition/Stage";
import {KnockoutStage} from "../../../models/competition/KnockoutStage";
import {Knockout} from "../../../models/competition/Knockout";
import {KnockoutService} from "../../../services/knockout/knockout.service";

@Component({
  selector: 'app-manage-knockout-stage',
  templateUrl: './manage-knockout-stage.component.html',
  styleUrls: ['./manage-knockout-stage.component.scss']
})
export class ManageKnockoutStageComponent implements OnInit {

  @Input()
  stage?: KnockoutStage;

  @Input()
  knockout: Knockout
  teamForm: any;

  constructor(private service: KnockoutService) { }

  ngOnInit() {
    console.log(this.stage.stageIndex)
  }

  valid(): boolean {
    let bool = true;
    this.stage.games.forEach(game => {
      if(game.teamTwo === null ||
        game.teamTwo.score === null ||
        game.teamOne === null ||
        game.teamOne.score === null){
        bool = false;
      }
    });
    if (this.stage.stageIndex  >= this.knockout.stages.length - 1){
      bool = false;
    }
    return bool;
  }

  onClick() {
    this.service.generateNextStage(this.knockout.name, this.stage.stageIndex).subscribe(res => {
    });
  }
}
