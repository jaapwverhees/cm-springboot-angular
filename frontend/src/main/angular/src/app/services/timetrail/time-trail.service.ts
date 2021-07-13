import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

import {TimeTrial} from "../../models/TimeTrial";
import {CreateTimeTrialRequest} from "../../models/CreateTimeTrialRequest";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TimeTrailService {
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private timeTrailURL = API_URL + '/timetrail';

  constructor(private http: HttpClient) {
  }

  public create(request: CreateTimeTrialRequest): Observable<TimeTrial> {
    return this.http.post<TimeTrial>(this.timeTrailURL, request, this.httpOptions);
  }

  getCompetition(id: string): Observable<TimeTrial> {
    return this.http.get<TimeTrial>(this.timeTrailURL, {
      headers: this.httpHeaders,
      params: new HttpParams().append('id', id.toString())
    })
  }
}
