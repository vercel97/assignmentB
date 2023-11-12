import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent {
  constructor(private router: Router, private authService: AuthService) { }

  navigateTo(route: string): void {
    this.router.navigateByUrl(route);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
