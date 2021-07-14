import {Component, OnInit} from '@angular/core';
import {CompetitionService} from "../services/competition/competition.service";
import {Competition} from "../models/competition/Competition";
import {Router} from "@angular/router";
import {AuthService} from "../auth/services/auth.service";
import {CompetitionResponse} from "../models/response/CompetitionResponse";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

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
    if(competition.type === 'TIMETRIAL'){
      this.router.navigate(['/manage-timeTrail', competition.name]);
    } else if(competition.type === 'TOURNAMENT') {
      this.router.navigate(['/manage-tournament', competition.name]);
    } else {
      this.router.navigate(['/pageNotFound', competition.name]);
    }
  }

  createCompetition() {
    this.router.navigate(['/pick-sport']);
  }
}
