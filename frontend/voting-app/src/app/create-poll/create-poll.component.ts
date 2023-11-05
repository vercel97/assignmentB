import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service';
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
      pollTitle: this.poll.pollTitle, // Assign the pollTitle from the poll
    };
    this.poll.questionList.push(newQuestion);
  }


  createPoll() {
    const currentUser = this.authService.getCurrentUser();
    if (currentUser && currentUser.id) {
      this.pollService.createPoll(this.poll, currentUser.id).subscribe(
        (response: Poll) => {
          // ...
        },
        (error) => {
          console.error("Error creating poll:", error);
        }
      );
    } else {
      console.error('No user is logged in.');
    }
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

