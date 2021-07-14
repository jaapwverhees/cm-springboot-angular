import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Team} from "../../models/competition/Team";
import {TimeTrialPrediction} from "../../models/prediction/TimeTrialPrediction";
import {environment} from "../../../environments/environment";
import {SetScoreRequest} from "../../models/requests/SetScoreRequest";
import {Score} from "../../models/competition/Score";
import {GamePrediction} from "../../models/prediction/GamePrediction";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private gameURL = API_URL + '/game';

  constructor(private http: HttpClient) {
  }

  BetGame(gameID: any, teamID: any): Observable<Team> {
    return this.http.get<Team>(this.gameURL + '/bet', {headers: this.httpHeaders,
      params: new HttpParams().append("gameID", gameID.toString())
        .append('teamID', teamID.toString())});
  }

  getPrediction(gameID: number): Observable<GamePrediction> {
    return this.http.get<GamePrediction>(this.gameURL + '/getPrediction', {headers: this.httpHeaders,
      params: new HttpParams().append("gameID", gameID.toString())});
  }

  getCorrectPredictions(stageID: number):Observable<string[]> {
    return this.http.get<string[]>(this.gameURL + '/getCorrectPredictions', {headers: this.httpHeaders,
      params: new HttpParams().append("stageID", stageID.toString())});
  }

  setScore(request: SetScoreRequest): Observable<Score> {
    return this.http.post<Score>(this.gameURL + '/score', request, this.httpOptions);
  }

}
