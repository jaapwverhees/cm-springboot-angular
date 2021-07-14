import {Component, OnInit} from '@angular/core';
import {CompetitionService} from "../services/competition/competition.service";
import {Router} from "@angular/router";
import {Competition} from "../models/competition/Competition";
import {CompetitionResponse} from "../models/response/CompetitionResponse";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.scss']
})
export class UserHomeComponent implements OnInit {

  constructor(private competitionService: CompetitionService,
              private router: Router) {
  }

  competitions: CompetitionResponse[]

  ngOnInit() {
    this.competitionService.findAll().subscribe(result => {
      this.competitions = result;
    });
  }

  selectCompetition(competition: CompetitionResponse) {
    if(competition.type === 'TIMETRAIL'){
      this.router.navigate(['/bet-timeTrail', competition.name]);
    } else if (competition.type === 'TOURNAMENT'){
      this.router.navigate(['/bet-tournament', competition.name]);
    }
  }

}
