import {Component, Input, OnInit} from '@angular/core';
import {Stage} from "../../models/Stage";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Score} from "../../models/Score";

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

  constructor(private fb:FormBuilder) { }

  ngOnInit() {
    console.log(this.stage);
    this.teamForm = this.fb.group({
      team: [null]
    });
  }

}
