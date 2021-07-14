import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Score} from "../../models/competition/Score";
import {CreateChampionShipRequest} from "../../models/requests/CreateChampionShipRequest";
import {Tournament} from "../../models/competition/Tournament";
import {logger} from "codelyzer/util/logger";
import {SetScoreRequest} from "../../models/requests/SetScoreRequest";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private championURL = API_URL + '/tournament';

  constructor(private http: HttpClient) {
  }

  public create(request: CreateChampionShipRequest): Observable<Tournament> {
    return this.http.post<Tournament>(this.championURL, request, this.httpOptions);
  }

  getCompetition(id: string): Observable<Tournament> {
    return this.http.get<Tournament>(this.championURL, {
      headers: this.httpHeaders,
      params: new HttpParams().append('id', id.toString())
    });
  }
  setScore(request: SetScoreRequest): Observable<Score> {
    return this.http.post<Score>(this.championURL + '/score', request, this.httpOptions);
  }

  getMostCorrectPredictions(id: string): Observable<string[]> {
    return this.http.get<string[]>(this.championURL + '/correctPredictions', {
      headers: this.httpHeaders,
      params: new HttpParams().append('id', id.toString())
    });
  }
}
