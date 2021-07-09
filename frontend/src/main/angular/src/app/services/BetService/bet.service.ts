import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../../models/Competition";
import {Team} from "../../models/Team";
import {Score} from "../../models/Score";
import {Stage} from "../../models/Stage";
import {Prediction} from "../../models/Prediction";
import {TimeTrailPrediction} from "../../models/TimeTrailPrediction";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class BetService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private timeTrailURL = API_URL + '/timetrail';

  constructor(private http: HttpClient) {
  }

  BetTimeTrail(stageID: number, teamID: number): Observable<Team> {
    return this.http.get<Team>(this.timeTrailURL + '/bet', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())
        .append('teamID', teamID.toString())});
  }

  getPrediction(stageID: number): Observable<TimeTrailPrediction> {
    return this.http.get<TimeTrailPrediction>(this.timeTrailURL + '/getPrediction', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())});
  }

  setWinner(id: number, id2: number) {

  }
}
