import {Component, OnInit} from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  login: string;
  password: string;
  errorMsg: string;

  constructor() {
  }

  ngOnInit() {
  }

  onFormSubmit(event) {
    //Проверка корректности заполненных полей
    if (!document.getElementById('email').classList.contains("valid")) {
      this.writeError("Invalid email");
      return;
    }
    if (!document.getElementById('password').classList.contains("valid")) {
      this.writeError("Invalid password");
      return;
    }

    //Зашифровка пароля
    var key = CryptoJS.enc.Base64.parse("#base64Key#");
    var iv = CryptoJS.enc.Base64.parse("#base64IV#");

    let encrypted: string = CryptoJS.AES.encrypt(this.password, key, {iv: iv}).toString();

  }

  /**
   * Вывести сообщение или ошибку
   * @param error текст сообщения или ошибки
   */
  writeError(error) {
    document.getElementById('errorHolder').classList.add("scale-in");
    this.errorMsg = error;
  }
}
