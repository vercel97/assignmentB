import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/AppUser';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const payload = { username, password };
    return this.http.post(`${this.apiUrl}/login`, payload);
  }

  logout() {
    localStorage.removeItem('authToken');
  }

  register(username: string, password: string) {
    const payload = { username, password };
    return this.http.post(`${this.apiUrl}`, payload);
  }


}

