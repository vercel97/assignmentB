import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/appUsers';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // 'Authorization': 'your-auth-token' // If you need to send an authorization token
    }),
   // withCredentials: true // If you need to include credentials such as cookies, authorization headers or TLS client certificates
  };

  constructor(private http: HttpClient) {}

  login(user: {username: string, password: string}) {
    return this.http.post(`${this.apiUrl}/login`, user, this.httpOptions).pipe(
      tap(response => console.log('Server response:', response))
    );
  }


  logout() {
    localStorage.removeItem('authToken');
  }

  register(user: {username: string, email: string, password: string}) {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

}
