

export interface Poll {
    id: number;
    isPrivate: boolean;
    duration: number;
    pairedIoT: any;
    pollTitle: string;
    authorizedUsers: any[];
    questionList: any[];
}
