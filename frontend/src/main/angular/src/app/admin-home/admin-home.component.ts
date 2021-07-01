import {Component, OnInit} from '@angular/core';
import {CompetitionService} from "../services/competition/competition.service";
import {Competition} from "../models/Competition";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private competitionService: CompetitionService,
              private router: Router) {
  }

  competitions: Competition[]

  ngOnInit() {
    this.competitionService.findAll().subscribe(result => {
      this.competitions = result;
      console.log(result);
    });
  }

  selectCompetition(competition: any) {
    this.router.navigate(['/manage-timeTrail', competition.name]);
  }

  createCompetition() {
    this.router.navigate(['/pick-sport']);
  }
}
