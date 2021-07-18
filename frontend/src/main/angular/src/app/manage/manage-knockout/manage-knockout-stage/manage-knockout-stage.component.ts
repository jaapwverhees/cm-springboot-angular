import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../../models/competition/Stage";
import {KnockoutStage} from "../../../models/competition/KnockoutStage";
import {Knockout} from "../../../models/competition/Knockout";
import {KnockoutService} from "../../../services/knockout/knockout.service";
import {Router} from "@angular/router";

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

  constructor(private service: KnockoutService,
              private router: Router) { }

  ngOnInit() {
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
      this.reloadComponent();
    });
  }

  reloadComponent() {
    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }
}
