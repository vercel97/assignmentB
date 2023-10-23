import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (response: any) => {
          // handle successful login
          // for example, store the token in local storage
        },
        (error: any) => {
          // handle login error
        }
      );
    }
  }

  onRegister() {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.register(username, password).subscribe(
        response => {
          alert('Registration successful!');
          this.loginForm.reset();
        },
        error => {
          alert('Registration failed! ' + error.error);
        }
      );
    } else {
      alert('Please fill out the form correctly.');
    }
  }
}












