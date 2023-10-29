import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, AbstractControl} from '@angular/forms';
import { PollService } from '../poll.service';
import {OnInit } from '@angular/core';


@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.css']
})

export class CreatePollComponent implements OnInit {


    constructor(private fb: FormBuilder, private pollService: PollService) {}
    pollForm: FormGroup = new FormGroup({});

    ngOnInit() {
      this.pollForm = this.fb.group({
          isPrivate: [false],
          duration: [''],
          pollTitle: [''],
          questions: this.fb.array([]),
          authorizedUsers: ['']
      });


    }



    get questions() {
        return this.pollForm.get('questions') as FormArray;
    }

    getFormControl(control: AbstractControl): FormControl {
        return control as FormControl;
    }


    addQuestion(question: string) {
        this.questions.push(this.fb.control(question));
    }

    removeQuestion(index: number) {
        this.questions.removeAt(index);
    }

    onSubmit() {
        this.pollService.createPoll(this.pollForm.value).subscribe(
            response => {
                console.log('Poll created:', response);
                // Redirect, vis en melding, etc. basert på hva du ønsker å gjøre etter at poll er opprettet.
            },
            error => {
                console.error('Error creating poll:', error);
            }
        );
    }
}
