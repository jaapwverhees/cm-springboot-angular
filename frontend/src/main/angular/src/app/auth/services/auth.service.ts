import {Injectable} from '@angular/core';

import {TokenService} from './token.service'
import {Credentials} from '../credentials';
import {HttpResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {JwtTokenCredentials} from "../../models/user/JwtTokenCredentials";
import {Roles} from "../../models/enums/Roles";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrlAdmin: string = '/admin-home';
  redirectToUrlUser: string = '/user-home';

  constructor(private router: Router, private tokenService: TokenService) {
  }

  public login(credentials: Credentials): void {
    this.tokenService.getResponseHeaders(credentials)
      .subscribe((res: HttpResponse<any>) => {
        this.saveToken(res.headers.get('authorization'));
        if(this.getRole() === Roles.ADMIN_ROLE){
          this.router.navigate([this.redirectToUrlAdmin]);
        }
        if(this.getRole() === Roles.USER_ROLE){
          this.router.navigate([this.redirectToUrlUser]);
        }
      });
  }

  private saveToken(token: string) {
    localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }

  public logout(): void {
    this.tokenService.logout()
      .subscribe(() => {
        localStorage.removeItem(AuthService.TOKEN_STORAGE_KEY);
      });
  }

  public isLoggedIn(): boolean {
    return !!this.getToken();
  }

  public getRole(): string {
    return JSON.parse(
      window.atob(
        localStorage.getItem(AuthService.TOKEN_STORAGE_KEY)
          .split('.')[1]))
      .authorities[0];
  }
}
