import {Component, OnInit} from '@angular/core';
import {Sports} from "../models/Sport";
import {Router} from "@angular/router";

@Component({
  selector: 'app-pick-sport',
  templateUrl: './pick-sport.component.html',
  styleUrls: ['./pick-sport.component.scss']
})
export class PickSportComponent implements OnInit {
  sports = [Sports.BICLYCE, Sports.FOOTBALL];

  constructor(private router: Router) { }

  ngOnInit() {
  }

  pickedSport(sport: Sports) {
    console.log(sport);
    this.router.navigate(['/pick-competition', { id: sport }]);
  }
}
