import {Component, OnInit} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  providers: []
})
export class UserPageComponent implements OnInit {

  user: User = new User('', '', '', '', '', '');
  errorMsgInfo: string;
  errorMsgPassword: string;

  oldPassword:string;
  newPassword:string;
  confirmNewPassword:string;

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
      this.writeErrorInfo('Invalid first name');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('lastName')).value === '') {
      this.writeErrorInfo('Invalid last name');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('birthday')).value === '') {
      this.writeErrorInfo('Invalid birthday');
      return;
    }

    this.userService.editUser(this.user).catch((response: Response) => {
      this.writeErrorInfo(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any) => {
      this.writeErrorInfo('Data updates successfully');
    });
  }

  onChangePasswordSubmit(event) {
    document.getElementById('changePasswordForm').classList.add('was-validated');

    if ((<HTMLInputElement>document.getElementById('inputPassword')).value === '') {
      this.writeErrorPassword('Invalid password');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('inputNewPassword')).value === '') {
      this.writeErrorPassword('Invalid new password');
      return;
    }

    var key = CryptoJS.enc.Base64.parse('#base64Key#');
    var iv = CryptoJS.enc.Base64.parse('#base64IV#');

    let encryptedOld: string = CryptoJS.AES.encrypt(this.oldPassword, key, {iv: iv}).toString();

    let encryptedNew: string = CryptoJS.AES.encrypt(this.newPassword, key, {iv: iv}).toString();

    let encryptedConfirmNew: string = CryptoJS.AES.encrypt(this.confirmNewPassword, key, {iv: iv}).toString();

    if (encryptedOld != this.user.password) {
      this.writeErrorPassword('Old password is incorrect');
      return;
    }
    if (encryptedNew != encryptedConfirmNew) {
      this.writeErrorPassword('Password confirmation does not match');
      return;
    }

    this.userService.editPassword(this.user.email, encryptedOld, encryptedNew).catch((response: Response) => {
      this.writeErrorPassword(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any) => {
      this.writeErrorPassword('Password changed successfully');
      this.user.password=encryptedNew;
    });
  }

  writeErrorInfo(error) {
    document.getElementById('collapseMessageInfo').classList.add('show');
    this.errorMsgInfo = error;
  }
  writeErrorPassword(error) {
    document.getElementById('collapseMessagePassword').classList.add('show');
    this.errorMsgPassword = error;
  }
}
