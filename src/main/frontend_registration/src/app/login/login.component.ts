import {Component, OnInit} from '@angular/core';
import * as CryptoJS from 'crypto-js';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';

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
    if (!document.getElementById('email').classList.contains('valid')) {
      this.writeError('Invalid email');
      return;
    }
    if (!document.getElementById('password').classList.contains('valid')) {
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
    }).subscribe(() => this.writeError('User authorized successfully.'));

  }

  /**
   * Вывести сообщение или ошибку
   * @param error текст сообщения или ошибки
   */
  writeError(error) {
    document.getElementById('errorHolder').classList.add('scale-in');
    this.errorMsg = error;
  }
}
