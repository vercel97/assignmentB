import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/register.component';
import { MainPageComponent } from './main-page/main-page.component';

import { CreatePollComponent } from './create-poll/create-poll.component';

import { FindPollComponent } from './find-poll/find-poll.component';
import { VoteComponent} from './vote/vote.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'main-page', component: MainPageComponent },
  { path: 'create-poll', component: CreatePollComponent },
  { path: 'find-poll', component: FindPollComponent },
  { path: 'vote/:id', component: VoteComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

