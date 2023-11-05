import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { AppUser } from './models/poll.model';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/appUsers';

  constructor(private http: HttpClient) {}

  login(user: { username: string, password: string }) {
    return this.http.post<AppUser>(`${this.apiUrl}/login`, user).pipe(
      tap(response => {
        localStorage.setItem('currentUser', JSON.stringify(response));
      })
    );
  }

  getCurrentUser(): AppUser | null {
    const userJson = localStorage.getItem('currentUser');
    return userJson ? JSON.parse(userJson) : null;
  }



  logout() {
    localStorage.removeItem('authToken');
  }

  register(user: {username: string, email: string, password: string}) {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  //used in the create poll component
  searchUsers(username: string): Observable<AppUser[]> {
    return this.http.get<AppUser[]>(`${this.apiUrl}/search`, { params: { username } });
  }

}


