import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Game} from "../../models/competition/Game";
import {GameRequest} from "../../models/requests/GameRequest";
import {KnockoutService} from "../../services/knockout/knockout.service";
import {CreateKnockoutRequest} from "../../models/requests/CreateKnockoutRequest";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-knockout',
  templateUrl: './create-knockout.component.html',
  styleUrls: ['./create-knockout.component.scss']
})
export class CreateKnockoutComponent implements OnInit {

  match: FormGroup;
  games: GameRequest[] = [];
  date: Date;
  name = new FormControl();

  constructor(private knockoutService: KnockoutService,
              private router: Router) { }

  ngOnInit() {
    this.match = new FormGroup({
      teamOne: new FormControl(),
      teamTwo: new FormControl()
    });
  }

  onFormSubmit(): void {
    let request = new GameRequest();
    request.teamOne = this.match.get('teamOne').value;
    request.teamTwo = this.match.get('teamTwo').value;
    this.match.get('teamOne').reset();
    this.match.get('teamTwo').reset();
    this.games.push(request);
  }

  deleteTeam(game: GameRequest) {
    for( var i = 0; i < this.games.length; i++){

      if ( this.games[i] === game) {
        this.games.splice(i, 1);
        i--;
      }
    }
  }

  addDate(lg: HTMLInputElement) {
    this.date = new Date(lg.value);
  }

  competitioninvalid() {
    return this.games.length < 2 || this.name === null || this.date < new Date();
  }

  create() {
    let request = new CreateKnockoutRequest()
    request.matches = this.games;
    request.name = this.name.value;
    request.maxDate = this.date;
    this.knockoutService.create(request).subscribe(result => {
      this.router.navigate(['/manage-knockout', result.name]);
    });
  }
}
