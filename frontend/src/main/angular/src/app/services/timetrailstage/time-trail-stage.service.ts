import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Team} from "../../models/competition/Team";
import {TimeTrialPrediction} from "../../models/prediction/TimeTrialPrediction";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TimeTrailStageService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private timeTrailStageURL = API_URL + '/timetrailStage';

  constructor(private http: HttpClient) {
  }

  BetTimeTrail(stageID: number, teamID: number): Observable<Team> {
    return this.http.get<Team>(this.timeTrailStageURL + '/bet', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())
        .append('teamID', teamID.toString())});
  }

  getPrediction(stageID: number): Observable<TimeTrialPrediction> {
    return this.http.get<TimeTrialPrediction>(this.timeTrailStageURL + '/getPrediction', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())});
  }

  getWinner(stageID: number): Observable<Team> {
    return this.http.get<Team>(this.timeTrailStageURL + '/getWinner', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())});
  }

  setWinner(stageID: number, teamID: number): Observable<Team> {
    return this.http.get<Team>(this.timeTrailStageURL + '/setWinner', {headers: this.httpHeaders, params: new HttpParams().append("stageID", stageID.toString()).append('teamID', teamID.toString())})
  }

  getCorrectPredictions(stageID: number):Observable<string[]> {
    return this.http.get<string[]>(this.timeTrailStageURL + '/getCorrectPredictions', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())});
  }
}
