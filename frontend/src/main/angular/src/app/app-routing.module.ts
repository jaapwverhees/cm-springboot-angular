import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './auth/components/login/login.component';
import {PickSportComponent} from "./pick-sport/pick-sport.component";
import {PickCompetitionComponent} from "./pick-competition/pick-competition.component";
import {CreateTimetrialComponent} from "./create-timetrail/create-timetrial.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {ManageTimeTrialComponent} from "./manage-time-trial/manage-time-trial.component";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {UserHomeComponent} from "./user-home/user-home.component";
import {AdminGuard} from "./auth/guards/adminguard/admin-guard";
import {UserGuard} from "./auth/guards/userguard/user-guard";
import {BetTimeTrailComponent} from "./bet-time-trail/bet-time-trail.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { canActivate: [AdminGuard], path: 'admin-home', component: AdminHomeComponent },
  { canActivate: [UserGuard], path: 'user-home', component: UserHomeComponent },
  { canActivate: [AdminGuard], path: 'pick-sport', component: PickSportComponent },
  { canActivate: [AdminGuard], path: 'pick-competition', component: PickCompetitionComponent },
  { canActivate: [AdminGuard], path: 'create-timeTrail', component: CreateTimetrialComponent },
  { canActivate: [AdminGuard], path: 'manage-timeTrail/:id', component: ManageTimeTrialComponent },
  { canActivate: [UserGuard], path: 'bet-timeTrail/:id', component: BetTimeTrailComponent },
  { path: 'pageNotFound', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
