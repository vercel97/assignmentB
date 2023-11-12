import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-current-user',
  templateUrl: './current-user.component.html',
  styleUrls: ['./current-user.component.css']
})
export class CurrentUserComponent implements OnInit {

  currentUser: string | null = null;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = localStorage.getItem('username');
  }

}
