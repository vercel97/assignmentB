import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value)
        .subscribe(
          (token: string) => {
            if (token) {
              localStorage.setItem('authToken', token);
              this.router.navigate(['/main-page']);
            } else {
              alert('Incorrect username or password.');
            }
          },
          (error: any) => {
            if (error.status === 401) {
              alert('Incorrect username or password.');
            } else {
              alert('An error occurred during login.');
            }
          }
        );
    }
  }
}
