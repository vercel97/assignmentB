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

  private handleError(error: HttpErrorResponse) {
    let errorMsg = '';
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMsg = `An error occurred: ${error.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      errorMsg = `Server returned code: ${error.status}, error message is: ${error.message}`;
    }
    console.error(errorMsg);
    // You can also log to server here if required
    return throwError(() => new Error(errorMsg));
  }

  searchUsers(username: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/search-users/${username}`);
  }

  getCurrentUser(): Observable<any> {
    // !! this is not implememnted
    return this.http.get<any>(`${this.baseUrl}/current-user`);
  }

  registerUser(userData: { username: string, email: string, password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, userData);
  }


}
