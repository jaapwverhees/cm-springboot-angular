import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "../../cookies/cookie";
import {CreateTimeTrailResponse} from "../../models/CreateTimeTrailResponse";
import {CreateTimeTrailRequest} from "../../models/CreateTimeTrailRequest";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TimeTrailService {

  private timeTrailURL = API_URL + '/timetrail';

  constructor(private http: HttpClient) { }

  public create(request: CreateTimeTrailRequest): Observable<CreateTimeTrailResponse> {
    return this.http.post<CreateTimeTrailResponse>(this.timeTrailURL, request, httpOptions);
  }

}
