// voting.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Poll, Question } from './models/poll.model';

@Injectable({
  providedIn: 'root'
})
export class VotingService {
  private apiUrl = 'http://localhost:8080/api/polls';

  constructor(private http: HttpClient) {}

  getPollById(id: number): Observable<Poll> {
    return this.http.get<Poll>(`${this.apiUrl}/${id}`);
  }

  updatePoll(poll: Poll): Observable<Poll> {
    return this.http.put<Poll>(`${this.apiUrl}/${poll.id}`, poll);
  }

  //... add more methods as needed
}
