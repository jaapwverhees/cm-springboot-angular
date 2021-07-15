import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {Router} from "@angular/router";
import {CreateTimeTrialRequest} from "../../models/requests/CreateTimeTrialRequest";
import {TournamentService} from "../../services/tournament/tournament.service";
import {CreateChampionShipRequest} from "../../models/requests/CreateChampionShipRequest";

@Component({
  selector: 'app-create-championship',
  templateUrl: './create-tournament.component.html',
  styleUrls: ['./create-tournament.component.scss']
})
export class CreateTournamentComponent implements OnInit {

  name = new FormControl();
  teams = [];
  date: Date;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private championService: TournamentService) { }

  ngOnInit() {
  }

  add(message: HTMLInputElement): void {
    this.teams.push(message.value);
    message.value = '';
  }

  deleteTeam(athlete: any) {
    const newItems = Array<string>();
    this.teams.forEach((element: string) => {
      if (element !== athlete){
        newItems.push(element);
      }
    });
    this.teams = newItems;
  }

  create() {
    let request = new CreateChampionShipRequest();
    request.name = this.name.value;
    request.teams = this.teams;
    request.date = this.date;
    this.championService.create(request).subscribe(result => {
      this.router.navigate(['/manage-tournament', result.name]);
    })
  }

  competitioninvalid():boolean {
    return this.teams.length < 1 && this.name === null;
  }

  addDate(lg: HTMLInputElement) {
    this.date = new Date(lg.value);
  }
}
