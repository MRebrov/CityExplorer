import {Component, OnInit} from '@angular/core';
import * as CryptoJS from 'crypto-js';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {AuthObject} from '../auth/authForm';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [UserService]
})
export class LoginComponent implements OnInit {
  login: string;
  password: string;
  errorMsg: string;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  onFormSubmit(event) {
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
      const authObject: AuthObject = obj.json();
      localStorage.setItem('id_token', authObject.token);
      this.writeError('User authorized successfully.');
    });
  }

  lalala() {
    this.userService.getName().subscribe((obj: any) => {
      this.writeError(obj);
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
}
