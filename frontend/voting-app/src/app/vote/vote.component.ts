// vote.component.ts
import { Component, Input, OnInit } from '@angular/core';
import { Poll, Question } from '../models/poll.model';
import { VotingService } from '../voting.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrls: ['./vote.component.css']
})
export class VoteComponent implements OnInit {
  @Input() poll: Poll = new Poll();

  constructor(private votingService: VotingService,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
      const pollIdStr = this.route.snapshot.paramMap.get('id');
      if (pollIdStr) {
          const pollId = +pollIdStr;
          this.fetchPoll(pollId);
      } else {
          console.error('Poll ID is missing from the route parameters.');
      }
  }
  voteOnQuestion(question: Question, response: boolean) {
    question.response = response;
    this.updatePoll();
  }

  fetchPoll(id: number) {
    this.votingService.getPollById(id).subscribe(
      response => {
        this.poll = response;
      },
      error => {
        console.error('Error fetching poll:', error);
      }
    );
  }

  private updatePoll() {
    this.votingService.updatePoll(this.poll).subscribe(
      response => {
        console.log('Vote registered!', response);
      },
      error => {
        console.error('Error updating poll:', error);
      }
    );
  }
}

