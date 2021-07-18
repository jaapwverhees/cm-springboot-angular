import { Component, OnInit } from '@angular/core';
import {Tournament} from "../../models/competition/Tournament";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {TournamentService} from "../../services/tournament/tournament.service";
import {Team} from "../../models/competition/Team";
import {Game} from "../../models/competition/Game";
import {Score} from "../../models/competition/Score";

@Component({
  selector: 'app-bet-tournament',
  templateUrl: './bet-tournament.component.html',
  styleUrls: ['./bet-tournament.component.scss']
})
export class BetTournamentComponent implements OnInit {

  tournament: Tournament;
  games: Game[];
  correctPredictors: string[];
  disabled: boolean;
  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private tournamentService: TournamentService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.tournamentService.getCompetition(params['id']).subscribe(result => {
        this.tournament = result;
        this.games = result.games;
        this.disabled = new Date(this.tournament.maxDate).getTime() > new Date().getTime();
        this.tournamentService.getMostCorrectPredictions(this.tournament.name).subscribe(result => {
          this.correctPredictors = result;
        });
      })
    });
  }

  getScores(game: Game): Score[] {
    return [game.teamOne, game.teamTwo]
  }
}

