import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {of} from "rxjs";
import {Sports} from "../models/Sport";
import {Competitions} from "../models/Competitions";

@Component({
  selector: 'app-pick-competition',
  templateUrl: './pick-competition.component.html',
  styleUrls: ['./pick-competition.component.scss']
})
export class PickCompetitionComponent implements OnInit {
  sport?: string
  competitions: string[];

  constructor(private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        of(params.get('id'))
      )
    ).subscribe((d) => {
      this.sport = d;
    });
    if (this.sport === Sports.BICLYCE) {
      this.competitions = [Competitions.TIMETRAIL]
    } else if (this.sport === Sports.FOOTBALL) {
      this.competitions = [Competitions.CHAMPOINSHIP, Competitions.KNOCKOUT]
    }
  }

  pickedCompetition(comp: string) {
    if (comp === Competitions.TIMETRAIL) {
      this.router.navigate(['create-timeTrail'])
    } else {
      this.router.navigate(['/pageNotFound']);
    }
  }
}
