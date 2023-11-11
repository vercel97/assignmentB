import { PollService } from '../poll.service';
import { Component, OnInit } from '@angular/core';
import { Poll, Question } from '../models/poll.model';

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

  constructor(private pollService:PollService) {}

  ngOnInit(): void {}

  addQuestion() {
    const newQuestion: Question = {
      questionText: '',
      response: false,
      username: '',
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


}
