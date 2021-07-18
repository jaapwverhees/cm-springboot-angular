import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {KnockoutService} from "../../services/knockout/knockout.service";
import {Knockout} from "../../models/competition/Knockout";
import {KnockoutStage} from "../../models/competition/KnockoutStage";

@Component({
  selector: 'app-manage-knockout',
  templateUrl: './manage-knockout.component.html',
  styleUrls: ['./manage-knockout.component.scss']
})
export class ManageKnockoutComponent implements OnInit {

  knockout: Knockout;
  stages: KnockoutStage[]
  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private knockoutService: KnockoutService) { }
  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.knockoutService.getKnockout(params['id']).subscribe(result => {
        this.knockout = result;
        this.stages = result.stages.sort((a,b) => a.stageIndex - b.stageIndex);
      });
    });
  }

}
