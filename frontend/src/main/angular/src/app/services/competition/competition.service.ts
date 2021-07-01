import {Injectable} from '@angular/core';
import {TimeTrail} from "../../models/TimeTrail";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Competition} from "../../models/Competition";
import {Observable} from "rxjs";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private competitionULR = API_URL + '/competition';

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.competitionULR, this.httpOptions);
  }
}
