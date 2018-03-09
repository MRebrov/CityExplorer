import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';
import {HeaderAuthorizedComponent} from './header-authorized/header-authorized.component';
import {HeaderUnauthorizedComponent} from './header-unauthorized/header-unauthorized.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  providers: []
})
export class HeaderComponent implements OnInit {


  constructor(public userService: UserService) {
  }

  ngOnInit() {

  }
}
