import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {TimeTrailService} from "../services/timetrail/time-trail.service";
import {CreateTimeTrailRequest} from "../models/CreateTimeTrailRequest";

@Component({
  selector: 'app-create-timetrail',
  templateUrl: './create-timetrail.component.html',
  styleUrls: ['./create-timetrail.component.scss']
})
export class CreateTimetrailComponent implements OnInit {

  roundes = [];
  name = new FormControl();
  athletes = [];

  constructor(private formBuilder: FormBuilder,
              private timeTrailService: TimeTrailService) { }

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
    let request = new CreateTimeTrailRequest();
    request.stages = this.roundes;
    request.name = this.name.value;
    request.teams = this.athletes;
    this.timeTrailService.create(request).subscribe(result => {
      console.log(result);
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
