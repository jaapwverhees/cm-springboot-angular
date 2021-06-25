import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";

@Component({
  selector: 'app-create-timetrail',
  templateUrl: './create-timetrail.component.html',
  styleUrls: ['./create-timetrail.component.scss']
})
export class CreateTimetrailComponent implements OnInit {

  roundes = new FormControl();
  name = new FormControl();
  athletes = ["bal", "bla"];

  constructor(private formBuilder: FormBuilder) { }

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
}
