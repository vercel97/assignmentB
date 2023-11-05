import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup; // Legg til denne linjen
  error: string = '';

  constructor(private authService: AuthService, private formBuilder: FormBuilder) {
    // Initialiser formgruppen med formbuilder
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required], // Legg til valideringer her om nødvendig
      password: ['', Validators.required] // Legg til valideringer her om nødvendig
    });
  }

  // onSubmit-metode for å håndtere formdata
  onSubmit(): void {
    if (this.loginForm.valid) {
      const username = this.loginForm.get('username')?.value;
      const password = this.loginForm.get('password')?.value;

      this.authService.loginUser(username, password).subscribe({
        next: (token: string) => {
          // Lagre token mottatt fra server
          localStorage.setItem('token', token);
          // Redirect brukeren eller gjør noe annet her
        },
        error: (err: any) => {
          this.error = err.error.message;
        }
      });
    }
  }
}
