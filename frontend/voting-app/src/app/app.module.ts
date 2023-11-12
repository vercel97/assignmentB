import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators, FormsModule } from '@angular/forms';

import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { PollService } from './poll.service';
import { AuthInterceptor } from './auth.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/register.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CreatePollComponent } from './create-poll/create-poll.component';
import { FindPollComponent } from './find-poll/find-poll.component';
import { VoteComponent } from './vote/vote.component';
import { CurrentUserComponent } from './current-user/current-user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    MainPageComponent,
    CreatePollComponent,
    FindPollComponent,
    VoteComponent,
    CurrentUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    PollService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


