import {Component, OnInit} from '@angular/core';
import * as CryptoJS from 'crypto-js';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {AuthObject} from '../auth/authForm';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: []
})
export class LoginComponent implements OnInit {
  login: string;
  password: string;
  errorMsg: string;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  }

  onFormSubmit(event) {
    document.getElementById('signInForm').classList.add('was-validated');
    // Проверка корректности заполненных полей
    const regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    if (!regexp.test((<HTMLInputElement>document.getElementById('email')).value)) {
      this.writeError('Invalid email');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('password')).value === '') {
      this.writeError('Invalid password');
      return;
    }

    // Зашифровка пароля
    const key = CryptoJS.enc.Base64.parse('#base64Key#');
    const iv = CryptoJS.enc.Base64.parse('#base64IV#');

    const encrypted: string = CryptoJS.AES.encrypt(this.password, key, {iv: iv}).toString();

    this.userService.authorize(this.login, encrypted).catch((response: Response) => {
      this.writeError(response.text()); // если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe((obj: any) => {
      this.writeError('User authorized successfully.');
      this.showLink();
    });
  }

  /**
   * Вывести сообщение или ошибку
   * @param error текст сообщения или ошибки
   */
  writeError(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

  showLink() {
    document.getElementById('collapseLink').classList.add('show');
  }
}
