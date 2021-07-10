import {AuthService} from "../../services/auth.service";
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Roles} from "../../../models/Roles";

@Injectable({
  providedIn: 'root'
})
export class UserGuard {

  constructor(private authService: AuthService, private router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let url: string = state.url;
    return this.checkLogin(url) && this.authService.getRole() === Roles.USER_ROLE;
  }

  private checkLogin(url: string): boolean {
    if(this.authService.isLoggedIn()) {
      return true;
    }
    this.authService.redirectToUrlAdmin = url;
    this.router.navigate(['/login']);
    return false;
  }
}
