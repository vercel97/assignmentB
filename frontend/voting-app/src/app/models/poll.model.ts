// poll.model.ts

export class Poll {
    id?: number = 0;
    isPrivate: boolean = false;
    duration: number = 0;
    pairedIoT?: IoTDevice;
    pollTitle: string = '';
    authorizedUsers: AppUser[] = [];
    questionList: Question[] = [];
    owner?: AppUser;
}

export class Question {
    id?: number = 0;
    questionText: string = '';
    response: boolean = false;
    pollTitle: string='';
}

export class AppUser {
    id?: number = 0;
    username: string = "";
    email: string = "";
    password: string = "";
    polls?: Poll[] = [];
}

export class IoTDevice {
    id?: number = 0;
    redVotes: number = 0;
    greenVotes: number = 0;
    pairedPoll?: Poll
}
