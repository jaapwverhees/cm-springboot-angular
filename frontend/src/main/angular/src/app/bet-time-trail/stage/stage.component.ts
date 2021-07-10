import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../models/Stage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Score} from "../../models/Score";
import {TimeTrailService} from "../../services/timetrail/time-trail.service";
import {TimeTrailStageService} from "../../services/timetrailstage/time-trail-stage.service";
import {DateUtil} from "../../util/date/date-util";

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
  winner?: string;
  teamForm : FormGroup;
  correctPredictions:string[] = [];
  disabled: boolean;

  constructor(private fb: FormBuilder,
              private timeTrailService: TimeTrailService,
              private timeTrailStageService: TimeTrailStageService) { }

  ngOnInit() {
    this.disabled = DateUtil.currentDateBeforeStage(new Date(this.stage.date));
    console.log(this.disabled);
    this.teamForm = this.fb.group({
      team: new FormControl({value: [null], disabled: this.disabled})
    });

    this.timeTrailStageService.getCorrectPredictions(this.stage.id).subscribe(res => {
      if(res.length > 0){
        this.correctPredictions = res;
      }

    })
    this.timeTrailStageService.getWinner(this.stage.id).subscribe(winner => {
      if(winner){
        this.winner = winner.name;
      }
    })
    this.timeTrailStageService.getPrediction(this.stage.id).subscribe(val => {
      if(val){
        this.teamForm.controls['team'].setValue(val.team.name);
      }
      this.onChanges();
    });
  }

  onChanges(): void {
    this.teamForm.valueChanges.subscribe(val => {
      this.stage.scores.forEach(score => {
        if(score.team.name === val.team){
          this.timeTrailStageService.BetTimeTrail(this.stage.id, score.team.id).subscribe(name => {
          //TODO noodzakelijk??
          });
        }
      });
    });
  }
}
