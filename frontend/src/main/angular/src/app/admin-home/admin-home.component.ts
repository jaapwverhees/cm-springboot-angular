import {Component, OnInit} from '@angular/core';
import {CompetitionService} from "../services/competition/competition.service";
import {Competition} from "../models/Competition";
import {Router} from "@angular/router";
import {AuthService} from "../auth/services/auth.service";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private competitionService: CompetitionService,
              private router: Router,
              private authService: AuthService) {
  }

  competitions: Competition[]

  ngOnInit() {
    this.competitionService.findAll().subscribe(result => {
      this.competitions = result;
    });
  }

  selectCompetition(competition: any) {
    this.router.navigate(['/manage-timeTrail', competition.name]);
  }

  createCompetition() {
    this.router.navigate(['/pick-sport']);
  }
}
