import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../models/Stage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {TimeTrailStageService} from "../../services/timetrailstage/time-trail-stage.service";

@Component({
  selector: 'app-manage-stage',
  templateUrl: './manage-stage.component.html',
  styleUrls: ['./manage-stage.component.scss']
})
export class ManageStageComponent implements OnInit {

  @Input()
  stage?: Stage;

  teamForm: FormGroup;

  disabled: boolean;

  constructor(private fb: FormBuilder,
              private timeTrailService: TimeTrailService,
              private timeTrailStageService: TimeTrailStageService) {
  }

  ngOnInit() {
    this.disabled = this.currentDateAfterStage();
    this.teamForm = this.fb.group({
      team: new FormControl({value: [null], disabled: this.disabled})
    });
    this.timeTrailStageService.getWinner(this.stage.id).subscribe(val => {
      if (val) {
        this.teamForm.controls['team'].setValue(val.name);
      }
      this.onChanges();
    });
  }

  currentDateAfterStage() {
    let d = new Date();
    var g1 = new Date(d.getFullYear(), d.getMonth(), d.getDate());
    return g1.getTime() < new Date(this.stage.date).getTime();
  }

  onChanges(): void {
    this.teamForm.valueChanges.subscribe(val => {
      this.stage.scores.forEach(score => {
        if (score.team.name === val.team) {
          this.timeTrailStageService.setWinner(this.stage.id, score.team.id).subscribe(name => {
            console.log(name);
          });
        }
      });
    });
  }

}
