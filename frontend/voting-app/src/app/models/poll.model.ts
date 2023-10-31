// poll.model.ts

//should I move the Vother and Question to their own models???
export class Voter {
    id?: number=0;
    username: string = '';
    // I need to implement the rest of the model

}


export class Poll {
    id?: number = 0;
    isPrivate: boolean = false;
    duration: number = 0;
    //pairedIoT?: IoTDevice;
    pollTitle: string = '';
    authorizedUsers: Voter[] = [];
    questionList: Question[] = [];
}

export class Question {
    id?: number = 0;
    questionText: string = '';
    response: boolean = false;
    username: string ='';
    pollTitle: string='';
}
