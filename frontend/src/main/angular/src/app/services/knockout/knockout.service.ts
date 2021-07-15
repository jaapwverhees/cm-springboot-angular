import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CreateChampionShipRequest} from "../../models/requests/CreateChampionShipRequest";
import {Observable} from "rxjs";
import {Tournament} from "../../models/competition/Tournament";
import {CreateKnockoutRequest} from "../../models/requests/CreateKnockoutRequest";
import {Knockout} from "../../models/competition/Knockout";

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class KnockoutService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private httpOptions = {
    headers: this.httpHeaders
  };

  private knockoutURL = API_URL + '/knockout';

  constructor(private http: HttpClient) {
  }

  public create(request: CreateKnockoutRequest): Observable<Knockout> {
    return this.http.post<Knockout>(this.knockoutURL, request, this.httpOptions);
  }

  getKnockout(id: string): Observable<Knockout> {
    return this.http.get<Knockout>(this.knockoutURL, {
      headers: this.httpHeaders,
      params: new HttpParams().append('id', id.toString())
    });
  }

  generateNextStage(name: string, stageIndex: number) {
    return this.http.get<Knockout>(this.knockoutURL + '/generate', {
      headers: this.httpHeaders,
      params: new HttpParams().set('knockoutID', name.toString())
        .set('stageIndex', stageIndex.toString())
    })

  }
}
