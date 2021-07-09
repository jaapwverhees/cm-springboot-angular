import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../models/Stage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
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
  winner?: string;
  teamForm : FormGroup;
  correctPredictions:string[] = [];
  disabled: boolean;

  constructor(private fb: FormBuilder,
              private betService: BetService,
              private timeTrailService: TimeTrailService) { }

  ngOnInit() {
    this.disabled = this.currentDateBeforeStage();
    console.log(this.disabled);
    this.teamForm = this.fb.group({
      team: new FormControl({value: [null], disabled: this.disabled})
    });

    this.timeTrailService.getCorrectPredictions(this.stage.id).subscribe(res => {
      if(res.length > 0){
        this.correctPredictions = res;
      }

    })
    this.timeTrailService.getWinner(this.stage.id).subscribe(winner => {
      if(winner){
        this.winner = winner.name;
      }
    })
    this.betService.getPrediction(this.stage.id).subscribe(val => {
      if(val){
        console.log('opgehaald: ' + val.team.name)
        this.teamForm.controls['team'].setValue(val.team.name);
      }
      this.onChanges();
    });
  }

  currentDateBeforeStage() {
    let d = new Date();
    var g1 = new Date(d.getFullYear(), d.getMonth(), d.getDate());
    return g1.getTime() > new Date(this.stage.date).getTime();
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
