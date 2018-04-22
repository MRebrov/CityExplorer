import { Component } from '@angular/core';
import {QuestService} from './quest/quest.service';
import {UserService} from './user/user.service';
import {Router} from '@angular/router';
import {LoginRedirectionService} from './login/login-redirection-service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'app';



  constructor(private userService: UserService, private loginRedirectionService: LoginRedirectionService) {


  }



  ngOnInit() {
    this.userService.initAuth();
    this.loginRedirectionService.init();
  }
}

