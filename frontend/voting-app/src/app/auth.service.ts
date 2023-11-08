// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';




@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/appUsers';


  constructor(private http: HttpClient) { }

  loginUser(username: string, password: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { username, password }).pipe(
      catchError(this.handleError)
    );
  }

  //trying to understand the error that occurs when logging in a user
  private handleError(error: HttpErrorResponse) {
    let errorMsg = '';
    if (error.error instanceof ErrorEvent) {
      errorMsg = `An error occurred: ${error.error.message}`;
    } else {
      errorMsg = `Server returned code: ${error.status}, error message is: ${error.message}`;
    }
    console.error(errorMsg);
    return throwError(() => new Error(errorMsg));
  }

  searchUsers(username: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/search-users/${username}`);
  }

  getCurrentUser(): Observable<any> {
    // this is not implememnted
    return this.http.get<any>(`${this.baseUrl}/current-user`);
  }

  registerUser(userData: { username: string, email: string, password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, userData);
  }


}
