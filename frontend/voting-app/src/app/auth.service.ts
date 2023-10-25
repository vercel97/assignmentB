import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/appUsers';

  constructor(private http: HttpClient) {}

  login(user: {username: string, password: string}) {
    return this.http.post(`${this.apiUrl}/login`, user);
  }

  logout() {
    localStorage.removeItem('authToken');
  }

  register(user: {username: string, email: string, password: string}) {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

}

