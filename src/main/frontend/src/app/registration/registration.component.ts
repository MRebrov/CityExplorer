import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';
import {User} from '../user/user.model';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import * as CryptoJS from 'crypto-js';
import {AppComponent} from '../app.component';
import {Router} from '@angular/router';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {PasswordValidation} from './password.validation';


/**
 * Основной компонент приложения
 */
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  user: User;
  confirmPassword: string;
  errorMsg: string;
  form: FormGroup;
  public barLabel: string = 'Password strength:';
  public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];
  loading: boolean = false;

  constructor(private userService: UserService, private router: Router, private fb: FormBuilder) {
    this.form = fb.group({
      'firstName': [null, Validators.required],
      'email': [null, Validators.compose([Validators.email, Validators.required])],
      'password': [null, Validators.compose([Validators.minLength(4),
        Validators.maxLength(24),
        Validators.required])],
      'confirmPassword': [null, Validators.required],
      'birthdate': '',
      'lastName': '',
    }, {
      validator: PasswordValidation.MatchPassword
    });
  }

  submitForm(data: FormGroup) {
    this.user.firstName = data.controls['firstName'].value;
    this.user.lastName = data.controls['lastName'].value;
    this.user.birthday = data.controls['birthdate'].value;
    this.user.email = data.controls['email'].value;

    var key = CryptoJS.enc.Base64.parse('#base64Key#');
    var iv = CryptoJS.enc.Base64.parse('#base64IV#');

    let encrypted: string = CryptoJS.AES.encrypt(data.controls['password'].value, key, {iv: iv}).toString();
    this.user.password = encrypted;

    this.loading=true;
    this.userService.addUser(this.user).catch((response: Response) => {
      this.writeError(response.text()); //если ошибка, вывести её
      this.loading=false;
      return Observable.throw(response);
    }).subscribe(() => {
      this.writeError('User registered successfully. We have sent you confirmation link on your email.');
      //this.showLink();
      this.loading=false;
      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 5000);
    });
    console.log(this.user.email + this.user.password);
    this.user = new User('', '', '', '', '', '', 50, null, 0);
  }

  ngOnInit() {
    if (this.userService._isAuthenticatedSubject.getValue()) {
      alert('You are authorized! You should log out first');
      this.router.navigate(['/map']);
    }
    this.user = new User('', '', '', '', '', '', 50, null, 0);
  }

  /**
   * Событие при нажатии кнопки submit
   * @param event
   */
  onFormSubmit(event) {
    document.getElementById('regForm').classList.add('was-validated');
    //Проверка корректности заполненных полей
    if ((<HTMLInputElement>document.getElementById('firstName')).value === '') {
      this.writeError('Invalid first name');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('lastName')).value === '') {
      this.writeError('Invalid last name');
      return;
    }
    const regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    if (!regexp.test((<HTMLInputElement>document.getElementById('email')).value)) {
      this.writeError('Invalid email');
      return;
    }
    if ((<HTMLInputElement>document.getElementById('password')).value === '') {
      this.writeError('Invalid password');
      return;
    }

    //Зашифровка пароля

    var key = CryptoJS.enc.Base64.parse('#base64Key#');
    var iv = CryptoJS.enc.Base64.parse('#base64IV#');

    let encrypted: string = CryptoJS.AES.encrypt(this.user.password, key, {iv: iv}).toString();
    let tUser: User;
    tUser = Object.assign({}, this.user);
    tUser.password = encrypted;

    //Зашифровка поля подтверждения пароля
    let encrypted2: string = CryptoJS.AES.encrypt(this.confirmPassword, key, {iv: iv}).toString();

    //Проверка что пароли совпадают
    if (tUser.password != encrypted2) {
      this.writeError('Passwords do not match');
      return;
    }

    //Отправляем запрос на сервер через сервис
    this.userService.addUser(tUser).catch((response: Response) => {
      this.writeError(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe(() => {
      this.writeError('User registered successfully. We have sent you confirmation link on your email.');
      //this.showLink();
      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 5000);

    }); //Если ошибки нет, сказать что регистрация прошла
  }

  isValidMail(mail: string) {
    const regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    return regexp.test(mail);
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
