import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth/guards/auth.guard';
import { LoginComponent } from './auth/components/login/login.component';
import {PickSportComponent} from "./pick-sport/pick-sport.component";
import {PickCompetitionComponent} from "./pick-competition/pick-competition.component";
import {CreateTimetrailComponent} from "./create-timetrail/create-timetrail.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {ManageTimeTrailComponent} from "./manage-time-trail/manage-time-trail.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { canActivate: [AuthGuard], path: 'pick-sport', component: PickSportComponent },
  { canActivate: [AuthGuard], path: 'pick-competition', component: PickCompetitionComponent },
  { canActivate: [AuthGuard], path: 'create-timeTrail', component: CreateTimetrailComponent },
  { canActivate: [AuthGuard], path: 'manage-timeTrail/:id', component: ManageTimeTrailComponent },
  { path: 'pageNotFound', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
