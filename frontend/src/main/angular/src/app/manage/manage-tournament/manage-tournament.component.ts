import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {TournamentService} from "../../services/tournament/tournament.service";
import {Tournament} from "../../models/competition/Tournament";

@Component({
  selector: 'app-manage-tournament',
  templateUrl: './manage-tournament.component.html',
  styleUrls: ['./manage-tournament.component.scss']
})
export class ManageTournamentComponent implements OnInit {

  tournament: Tournament;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private tournamentService: TournamentService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.tournamentService.getCompetition(params['id']).subscribe(result => {
        this.tournament = result;
      })
    });
  }

}
