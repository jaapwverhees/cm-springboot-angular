import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {CreateTimeTrialRequest} from "../models/CreateTimeTrialRequest";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-timetrail',
  templateUrl: './create-timetrial.component.html',
  styleUrls: ['./create-timetrial.component.scss']
})
export class CreateTimetrialComponent implements OnInit {

  roundes = [];
  name = new FormControl();
  athletes = [];

  constructor(private formBuilder: FormBuilder,
              private timeTrailService: TimeTrailService,
              private router: Router) { }

  ngOnInit() {
  }

  add(message: HTMLInputElement): void {
    this.athletes.push(message.value);
    message.value = '';
  }

  deleteAthlete(athlete: any) {
    const newItems = Array<string>();
    this.athletes.forEach((element: string) => {
      if (element !== athlete){
        newItems.push(element);
      }
    });
    this.athletes = newItems;
  }

  create() {
    let request = new CreateTimeTrialRequest();
    request.stages = this.roundes;
    request.name = this.name.value;
    request.teams = this.athletes;
    this.timeTrailService.create(request).subscribe(result => {
      this.router.navigate(['/manage-timeTrail', result.name]);
    })
  }

  addRound(logo: HTMLInputElement) {
    this.roundes.push(logo.value);
    logo.value = '';
  }

  deleteround(athlete: any) {
    const newItems = Array<string>();
    this.roundes.forEach((element: string) => {
      if (element !== athlete){
        newItems.push(element);
      }
    });
    this.athletes = newItems;
  }

  competitioninvalid():boolean {
    return this.roundes.length < 1 || this.athletes.length < 1 && this.name === null;
  }
}
