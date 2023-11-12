import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegistrationComponent {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const { username, password } = this.registerForm.value;
      this.authService.login({username, password})
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
    if (this.registerForm.valid) {
      const { username, email, password } = this.registerForm.value;
      this.authService.register({username, email, password}).subscribe(
        response => {
          alert('Registration successful!');
          this.registerForm.reset();
          this.router.navigate(['/login']);
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

