import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/appUsers';

  constructor(private http: HttpClient) {}

  login(user: { username: string, password: string }) {
    return this.http.post<string>(`${this.apiUrl}/login`, user, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text' as 'json'
    }).pipe(
      tap(response => {
        localStorage.setItem('authToken', response);
        localStorage.setItem('username', user.username);
        console.log('Server response:', response);
      })
    );
  }

  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('username');
  }

  register(user: { username: string, email: string, password: string }) {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  getAuthToken() {
    return localStorage.getItem('authToken');
  }
}
