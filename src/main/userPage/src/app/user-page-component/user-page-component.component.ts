import { Component, OnInit } from '@angular/core';
import { User } from "../user.model";
import {UserService} from "../user.service";
import {Http, Response} from '@angular/http';

@Component({
  selector: 'app-user-page-component',
  templateUrl: './user-page-component.component.html',
  providers: [UserService],
  styleUrls: ['./user-page-component.component.css']
})
export class UserPageComponentComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    //this.user = new User("Ivan", "Ivanov", "17.02.18", "ivan@ivanov.ru", "ivanrules", "18.02.18");
    this.userService.getUser('ivanko@mail.ru')
      .subscribe(
        (user: any) => {
          this.user = user
        },
        (error) => console.log(error)
      );


  }
}
