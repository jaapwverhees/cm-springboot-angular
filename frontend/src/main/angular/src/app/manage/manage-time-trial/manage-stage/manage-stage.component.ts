import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../../models/competition/Stage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TimeTrailService} from "../../../services/timetrail/time-trail.service";
import {TimeTrailStageService} from "../../../services/timetrailstage/time-trail-stage.service";
import {DateUtil} from "../../../util/date/date-util";

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
    this.disabled = DateUtil.currentDateAfterStage(new Date(this.stage.date));
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

  onChanges(): void {
    this.teamForm.valueChanges.subscribe(val => {
      this.stage.scores.forEach(score => {
        if (score.team.name === val.team) {
          this.timeTrailStageService.setWinner(this.stage.id, score.team.id).subscribe(name => {
          });
        }
      });
    });
  }

}
