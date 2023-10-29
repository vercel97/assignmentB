import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators, FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { PollService } from './poll.service';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/register.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CreatePollComponent } from './create-poll/create-poll.component';
import { FindPollComponent } from './find-poll/find-poll.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    MainPageComponent,
    CreatePollComponent,
    FindPollComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    FormsModule
  ],
  providers: [PollService],
  bootstrap: [AppComponent]
})
export class AppModule { }


