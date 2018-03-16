import {Component, OnInit} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  providers: []
})
export class UserPageComponent implements OnInit {

  user: User = new User('', '', '', '', '', '');
  errorMsg: string;

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

  onPersonalInfoSubmit(event) {

    document.getElementById('infoForm').classList.add('was-validated');

    if ((<HTMLInputElement>document.getElementById('firstName')).value === '') {
      this.writeError('Invalid first name');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('lastName')).value === '') {
      this.writeError('Invalid last name');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('birthday')).value === '') {
      this.writeError('Invalid birthday');
      return;
    }

    this.userService.editUser(this.user).catch((response: Response) => {
      this.writeError(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any) => {
      this.writeError('Data updates successfully');
    });
  }

  onChangePasswordSubmit(event) {
  }

  writeError(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }
}
