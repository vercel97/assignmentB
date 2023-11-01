import { Component } from '@angular/core';
import { Poll } from '../models/poll.model';
import { PollService } from '../poll.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-find-poll',
  templateUrl: './find-poll.component.html',
  styleUrls: ['./find-poll.component.css']
})
export class FindPollComponent {
  searchId: number = 0;
  searchTitle: string = '';
  poll: Poll | null = null;
  polls: Poll[] = [];


  constructor(private pollService: PollService, private router:Router) {}

  findPoll() {
    this.pollService.findPoll(this.searchId).subscribe(
      response => {
        this.poll = response;
      },
      error => {
        console.error('Error finding poll:', error);
      }
    );
  }

  searchPollsByTitle() {
      this.pollService.searchPollsByTitle(this.searchTitle).subscribe(
          response => {
              this.polls = response;  // Here, assign to "polls" not "poll"
          },
          error => {
              console.error('Error searching polls by title:', error);
          }
      );
  }

  voteOnPoll(pollId?: number) {
      if (pollId !== undefined) {
          this.router.navigate(['/vote', pollId]);  // Antatt rute: '/vote/:id'
      } else {
          console.error('Poll ID is undefined.');
      }
  }


}

//implement option to find by id and by title

