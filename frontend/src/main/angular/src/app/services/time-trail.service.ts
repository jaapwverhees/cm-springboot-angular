import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Cookie} from "../cookies/cookie";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TimeTrailService {

  private TimeTrailUrl = API_URL + '/timetrail';

  constructor(private http: HttpClient) { }

  public createTimetrail(): Observable<Cookie[]> {
    console.log("fuction")
    return this.http.post(this.TimeTrailUrl, httpOptions);
  }
}
