import {Component, OnInit} from '@angular/core';
import * as CryptoJS from 'crypto-js';
import {UserService} from '../user/user.service';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {AuthObject} from '../auth/authForm';
import {ActivatedRoute, Router} from '@angular/router';
import {AppRoutingModule} from '../app-routing.module';
import {LoginRedirectionService} from './login-redirection-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: []
})
export class LoginComponent implements OnInit {
  login: string;
  password: string;
  errorMsg: string;

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router, private loginRedicrectionService: LoginRedirectionService) {
  }

  ngOnInit() {
    if (this.userService._isAuthenticatedSubject.getValue()) {
      alert('You are authorized! You should log out first');
      this.router.navigate(['/map']);
    }

    this.route.params.subscribe(params => {
      if(params['state']=='activated'){
        this.writeError('Your account has been activated successfully. You can sign in now.')
      }
      else if(params['state']=='invalid'){
        this.writeError('Invalid confirmation link.')
      }
      else if(params['state']=='expired'){
        this.writeError('Your confirmation link has expireds.')
      }
    });
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
      this.writeError('User authorized successfully. You will be redirected now...');
      this.successLogin();
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

  successLogin() {
    //document.getElementById('collapseLink').classList.add('show');
    setTimeout(() => {
      this.router.navigate(['/map']);
    }, 5000);
    ;

  }
}
