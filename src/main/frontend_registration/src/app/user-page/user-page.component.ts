import {Component, OnInit} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  providers: []
})
export class UserPageComponent implements OnInit {

  user: User = new User('', '', '', '', '', '');

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.userService.getCurrentUser()
      .subscribe(
        (user: any) => {
          this.user = user;
        },
        (error) => {
          console.log(error);
          this.router.navigate(['/login']);
        });


  }
}
