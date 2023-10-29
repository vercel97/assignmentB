import { Component } from '@angular/core';
import { Poll } from '../models/poll.model';
import { PollService } from '../poll.service';



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


  constructor(private pollService: PollService) {}

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

}

//implement option to find by id and by title

