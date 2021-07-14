import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TournamentService} from "../services/tournament/tournament.service";

@Component({
  selector: 'app-correct-predictions',
  templateUrl: './correct-predictions.component.html',
  styleUrls: ['./correct-predictions.component.scss']
})
export class CorrectPredictionsComponent implements OnInit {
  @Input()
  correctPredictors: string[];

  constructor() {}

  ngOnInit() {}

}
