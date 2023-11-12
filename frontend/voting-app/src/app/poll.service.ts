import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Poll} from './models/poll.model';


@Injectable({
  providedIn: 'root'
})
export class PollService {

  private apiUrl = 'http://localhost:8080/api/polls';

  constructor(private http: HttpClient) { }

  getAllPolls(): Observable<Poll[]> {
    return this.http.get<Poll[]>(this.apiUrl);
  }

  getPollById(id: number): Observable<Poll> {
    return this.http.get<Poll>(`${this.apiUrl}/${id}`);
  }

  createPoll(poll: Poll): Observable<Poll> {
    return this.http.post<Poll>(this.apiUrl, poll);
  }

  updatePoll(id: number, poll: Poll): Observable<Poll> {
    return this.http.put<Poll>(`${this.apiUrl}/${id}`, poll);
  }

  deletePoll(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

findPoll(id: number): Observable<Poll> {
    return this.http.get<Poll>(`${this.apiUrl}/${id}`);
}

searchPollsByTitle(title: string): Observable<Poll[]> {
    return this.http.get<Poll[]>(`${this.apiUrl}/search?title=${title}`);
}


}

