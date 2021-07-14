import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './auth/components/login/login.component';
import {PickSportComponent} from "./pick/pick-sport/pick-sport.component";
import {PickCompetitionComponent} from "./pick/pick-competition/pick-competition.component";
import {CreateTimetrialComponent} from "./create/create-timetrail/create-timetrial.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {ManageTimeTrialComponent} from "./manage/manage-time-trial/manage-time-trial.component";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {UserHomeComponent} from "./user-home/user-home.component";
import {AdminGuard} from "./auth/guards/adminguard/admin-guard";
import {UserGuard} from "./auth/guards/userguard/user-guard";
import {BetTimeTrailComponent} from "./bet/bet-time-trail/bet-time-trail.component";
import {CreateTournamentComponent} from "./create/create-tournament/create-tournament.component";
import {ManageTournamentComponent} from "./manage/manage-tournament/manage-tournament.component";
import {BetTournamentComponent} from "./bet/bet-tournament/bet-tournament.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { canActivate: [AdminGuard], path: 'admin-home', component: AdminHomeComponent },
  { canActivate: [UserGuard], path: 'user-home', component: UserHomeComponent },
  { canActivate: [AdminGuard], path: 'pick-sport', component: PickSportComponent },
  { canActivate: [AdminGuard], path: 'pick-competition', component: PickCompetitionComponent },
  { canActivate: [AdminGuard], path: 'create-timeTrail', component: CreateTimetrialComponent },
  { canActivate: [AdminGuard], path: 'create-tournament', component: CreateTournamentComponent },
  { canActivate: [AdminGuard], path: 'manage-timeTrail/:id', component: ManageTimeTrialComponent },
  { canActivate: [AdminGuard], path: 'manage-tournament/:id', component: ManageTournamentComponent },
  { canActivate: [UserGuard], path: 'bet-timeTrail/:id', component: BetTimeTrailComponent },
  { canActivate: [UserGuard], path: 'bet-tournament/:id', component: BetTournamentComponent },
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
