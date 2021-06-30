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
import { PickSportComponent } from './pick-sport/pick-sport.component';
import { PickCompetitionComponent } from './pick-competition/pick-competition.component';
import { CreateTimetrailComponent } from './create-timetrail/create-timetrail.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ManageTimeTrailComponent } from './manage-time-trail/manage-time-trail.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    PickSportComponent,
    PickCompetitionComponent,
    CreateTimetrailComponent,
    PageNotFoundComponent,
    ManageTimeTrailComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
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
