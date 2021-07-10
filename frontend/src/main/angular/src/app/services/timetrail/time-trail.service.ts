import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

import {TimeTrail} from "../../models/TimeTrail";
import {CreateTimeTrailRequest} from "../../models/CreateTimeTrailRequest";

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

  public create(request: CreateTimeTrailRequest): Observable<TimeTrail> {
    return this.http.post<TimeTrail>(this.timeTrailURL, request, this.httpOptions);
  }

  getCompetition(id: string): Observable<TimeTrail> {
    return this.http.get<TimeTrail>(this.timeTrailURL, {
      headers: this.httpHeaders,
      params: new HttpParams().append('id', id.toString())
    })
  }
}
