import {Component, OnInit} from "@angular/core";
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";
import {Http, Response} from '@angular/http';
import {Observable} from "rxjs/Observable";
import * as CryptoJS from 'crypto-js';
import {AppComponent} from "../app.component";

/**
 * Основной компонент приложения
 */
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  providers: [UserService]
})
export class RegistrationComponent implements OnInit {
  user: User;
  confirmPassword: string;
  errorMsg: String;

  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.user = new User("", "", "", "", "");
  }

  /**
   * Событие при нажатии кнопки submit
   * @param event
   */
  onFormSubmit(event) {
    //Проверка корректности заполненных полей
    if(!document.getElementById('firstName').classList.contains("valid")){
      this.writeError("Invalid first name");
      return;
    }
    if(!document.getElementById('lastName').classList.contains("valid")){
      this.writeError("Invalid last name");
      return;
    }
    if(!document.getElementById('email').classList.contains("valid")){
      this.writeError("Invalid email");
      return;
    }
    if(!document.getElementById('password').classList.contains("valid")){
      this.writeError("Invalid password");
      return;
    }

    //Зашифровка пароля
    var key = CryptoJS.enc.Base64.parse("#base64Key#");
    var iv  = CryptoJS.enc.Base64.parse("#base64IV#");

    let encrypted: string = CryptoJS.AES.encrypt(this.user.password, key, {iv: iv}).toString();
    let tUser:User;
    tUser=Object.assign({}, this.user);
    tUser.password=encrypted;

    //Зашифровка поля подтверждения пароля
    let encrypted2: string = CryptoJS.AES.encrypt(this.confirmPassword, key, {iv: iv}).toString();

    //Проверка что пароли совпадают
    if(tUser.password != encrypted2){
      this.writeError("Passwords do not match");
      return;
    }

    //Отправляем запрос на сервер через сервис
    this.userService.addUser(tUser).catch((response:Response) => {
      this.writeError(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe(()=>this.writeError("User registered successfully. We have sent you confirmation link on your email.")); //Если ошибки нет, сказать что регистрация прошла
  }

  /**
   * Вывести сообщение или ошибку
   * @param error текст сообщения или ошибки
   */
  writeError(error){
    document.getElementById('errorHolder').classList.add("scale-in");
    this.errorMsg=error;
  }
}
