import {Component, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-time-trail-score',
  templateUrl: './time-trail-score.component.html',
  styleUrls: ['./time-trail-score.component.scss']
})
export class TimeTrailScoreComponent implements OnInit {

  @Input()
  totalInput:number
  @Output()
  totalOutput: number
  hours: number;
  minutes: number
  seconds: number;
  milliseconds: number;

  constructor() { }

  ngOnInit() {
  }

  setHour(input: HTMLInputElement) {

  }

  setMinutes(input: HTMLInputElement) {

  }

  setSeconds(input: HTMLInputElement) {

  }

  setMilliSeconds(input: HTMLInputElement) {

  }
}
