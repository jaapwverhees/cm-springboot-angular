import {Component, OnInit} from '@angular/core';
import {CompetitionService} from "../services/competition/competition.service";
import {Router} from "@angular/router";
import {Competition} from "../models/Competition";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.scss']
})
export class UserHomeComponent implements OnInit {

  constructor(private competitionService: CompetitionService,
              private router: Router) {
  }

  competitions: Competition[]

  ngOnInit() {
    this.competitionService.findAll().subscribe(result => {
      this.competitions = result;
    });
  }

  selectCompetition(competition: any) {
    this.router.navigate(['/bet-timeTrail', competition.name]);
  }

}
