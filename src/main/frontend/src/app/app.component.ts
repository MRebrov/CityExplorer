import {Component} from '@angular/core';
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


  constructor(private userService: UserService, private loginRedirectionService: LoginRedirectionService, private questService: QuestService) {


  }


  ngOnInit() {
    this.userService.onNotifiedHandler=(message) => {
      if (message.body) {
        alert(message.body);
        this.questService.getConfirmationsList();
      }
    };
    setTimeout(() => {
      this.userService.initAuth();
    }, 1500);
    ;
    setTimeout(() => {
      this.questService.getConfirmationsList();//to show confirmations count on header
    }, 6000);
    ;

    this.loginRedirectionService.init();
  }
}

