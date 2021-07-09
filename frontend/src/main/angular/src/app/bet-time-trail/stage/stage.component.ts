import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../models/Stage";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Score} from "../../models/Score";
import {BetService} from "../../services/BetService/bet.service";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";

@Component({
  selector: 'app-stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.scss']
})
export class StageComponent implements OnInit {

  @Input()
  stage?: Stage;
  @Input()
  scores?: Score[];

  teamForm : FormGroup;

  constructor(private fb: FormBuilder,
              private betService: BetService,
              private timeTrailService: TimeTrailService) { }

  ngOnInit() {
    this.teamForm = this.fb.group({
      team: [null]
    });
    this.betService.getPrediction(this.stage.id).subscribe(val => {
      console.log('opgehaald: ' + val.team.name)
      this.teamForm.controls['team'].setValue(val.team.name);
      this.onChanges();
    });
  }
  onChanges(): void {
    this.teamForm.valueChanges.subscribe(val => {
      this.stage.scores.forEach(score => {
        if(score.team.name === val.team){
          this.betService.BetTimeTrail(this.stage.id, score.team.id).subscribe(name => {
            console.log(name);
          });
        }
      });
    });
  }

}
