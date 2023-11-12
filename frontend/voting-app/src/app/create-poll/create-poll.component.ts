import { PollService } from '../poll.service';
import { Component, OnInit } from '@angular/core';
import { Poll, Question, AppUser } from '../models/poll.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.css']
})

export class CreatePollComponent implements OnInit {
  poll: Poll = {
    isPrivate: false,
    duration: 0,
    pollTitle: '',
    authorizedUsers: [],
    questionList: []
  };

  searchResults: AppUser[] = [];
  createdPollId: number | null = null;

  constructor(private pollService: PollService, private authService: AuthService) {}

  ngOnInit(): void {}

  searchUsers(username: string) {
    this.authService.searchUsers(username)
      .subscribe(
        (users: AppUser[]) => {
          this.searchResults = users;
        },
        error => {
          console.error('Search failed:', error);
        }
      );
  }

  addAuthorizedUser(user: AppUser) {
    this.poll.authorizedUsers.push(user);
  }

  addQuestion() {
    const newQuestion: Question = {
      questionText: '',
      response: false,
      // username: '',
      pollTitle: ''
    };
    this.poll.questionList.push(newQuestion);
  }



  createPoll() {
      this.pollService.createPoll(this.poll).subscribe(
          (response: Poll) => {
              // Gjør noe med responsen, for eksempel vise en melding om at avstemningen ble opprettet.
              console.log("Poll created successfully!", response);
              // Du kan også tilbakestille skjemaet eller navigere til en annen side.
          },
          (error) => {
              console.error("Error creating poll:", error);
              // Håndter feilen, for eksempel ved å vise en feilmelding til brukeren.
          }
      );
  }

  copyPollIdToClipboard(id: number | null) {
    if (id !== null) {
      const el = document.createElement('textarea');
      el.value = id.toString();
      document.body.appendChild(el);
      el.select();
      document.execCommand('copy');
      document.body.removeChild(el);
    }
  }
}
