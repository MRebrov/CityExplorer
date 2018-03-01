import {Http, Response} from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import {User} from './user.model';
import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {AuthHttp} from 'angular2-jwt';

/**
 * Сервис на frontend
 * rest запросы на серверс представляются методами
 */
@Injectable()
export class UserService {

  constructor(public authHttp: AuthHttp, public http: Http) {
  }

  /**
   * Отправить запрос на добавление пользователя
   * @param {User} user Пользователь которого добавить
   * @returns {Observable<any>}
   */
  addUser(user: User) {
    return this.http.post('userapi/add', user).map(res => res.json);
  }

  authorize(username: string, password: string) {
    return this.http.post('userapi/authorize', {username: username, password: password}).map((response: Response) => {
      return response;
    });
  }

  getName() {
    return this.authHttp.get('/userapi/testName').map((response: Response) => {
      return response.text();
    });
  }

  getCurrentUser() {
    return this.authHttp.get('userapi/get/loggedIn').map((response: Response) => {
      return response.json();
    });
  }
}
