import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Competition} from "../../models/competition/Competition";
import {Observable} from "rxjs";
import {CompetitionResponse} from "../../models/response/CompetitionResponse";

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

  findAll(): Observable<CompetitionResponse[]> {
    return this.http.get<CompetitionResponse[]>(this.competitionULR, this.httpOptions);
  }
}
