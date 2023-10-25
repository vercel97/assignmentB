import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register', // Updated selector
  templateUrl: './register.component.html', // Updated templateUrl
  styleUrls: ['./register.component.css'] // Update this if you have a corresponding CSS file for registration
})

export class RegistrationComponent {

  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.required]

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

