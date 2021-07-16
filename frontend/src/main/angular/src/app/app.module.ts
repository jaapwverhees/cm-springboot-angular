import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/components/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtTokenInterceptor } from './auth/interceptors/jwt.token.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { PickSportComponent } from './pick/pick-sport/pick-sport.component';
import { PickCompetitionComponent } from './pick/pick-competition/pick-competition.component';
import { CreateTimetrialComponent } from './create/create-timetrail/create-timetrial.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ManageTimeTrialComponent } from './manage/manage-time-trial/manage-time-trial.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { BetTimeTrailComponent } from './bet/bet-time-trail/bet-time-trail.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import { StageComponent } from './bet/bet-time-trail/stage/stage.component';
import { ManageStageComponent } from './manage/manage-time-trial/manage-stage/manage-stage.component';
import { CreateTournamentComponent } from './create/create-tournament/create-tournament.component';
import { ManageTournamentComponent } from './manage/manage-tournament/manage-tournament.component';
import { ManageGamesComponent } from './manage/manage-tournament/manage-games/manage-games.component';
import { BetTournamentComponent } from './bet/bet-tournament/bet-tournament.component';
import { GamesComponent } from './bet/bet-tournament/games/games.component';
import { CorrectPredictionsComponent } from './correct-predictions/correct-predictions.component';
import { CreateKnockoutComponent } from './create/create-knockout/create-knockout.component';
import { ManageKnockoutComponent } from './manage/manage-knockout/manage-knockout.component';
import { ManageKnockoutStageComponent } from './manage/manage-knockout/manage-knockout-stage/manage-knockout-stage.component';
import { ManageKnockoutStageGameComponent } from './manage/manage-knockout/manage-knockout-stage/manage-knockout-stage-game/manage-knockout-stage-game.component';
import { BetKnockOutComponent } from './bet/bet-knock-out/bet-knock-out.component';
import { KnockoutStageComponent } from './bet/bet-knock-out/knockout-stage/knockout-stage.component';
import { BetKnockoutGameComponent } from './bet/bet-knock-out/knockout-stage/bet-knockout-game/bet-knockout-game.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    PickSportComponent,
    PickCompetitionComponent,
    CreateTimetrialComponent,
    PageNotFoundComponent,
    ManageTimeTrialComponent,
    AdminHomeComponent,
    UserHomeComponent,
    BetTimeTrailComponent,
    StageComponent,
    ManageStageComponent,
    CreateTournamentComponent,
    ManageTournamentComponent,
    ManageGamesComponent,
    BetTournamentComponent,
    GamesComponent,
    CorrectPredictionsComponent,
    CreateKnockoutComponent,
    ManageKnockoutComponent,
    ManageKnockoutStageComponent,
    ManageKnockoutStageGameComponent,
    BetKnockOutComponent,
    KnockoutStageComponent,
    BetKnockoutGameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtTokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
