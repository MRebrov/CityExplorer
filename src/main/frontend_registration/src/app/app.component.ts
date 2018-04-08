import { Component } from '@angular/core';
import {QuestService} from './quest/quest.service';
import {UserService} from './user/user.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'app';



  constructor(private userService: UserService) {


  }



  ngOnInit() {
    this.userService.initAuth();
  }
}

