import {Http, Response} from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import {User} from './user.model';
import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {AuthHttp} from 'angular2-jwt';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {AuthObject} from '../auth/authForm';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

/**
 * Сервис на frontend
 * rest запросы на серверс представляются методами
 */
@Injectable()
export class UserService {

  public _isAuthenticatedSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isAuthenticatedObs: Observable<boolean> = this._isAuthenticatedSubject.asObservable();

  private serverSocketUrl = '/socket'
  private stompClient;

  constructor(public authHttp: AuthHttp, public http: Http) {
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverSocketUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/user/confirmation", (message) => {
        if(message.body) {
          alert(message.body);
        }
      });
    });

  }

  /**
   * Отправить запрос на добавление пользователя
   * @param {User} user Пользователь которого добавить
   * @returns {Observable<any>}
   */
  addUser(user: User) {
    return this.http.post('userapi/add', user).map(res => res.json);
  }

  editUser(user: User) {
    return this.authHttp.post('userapi/edit/personalInfo', user).map((response: Response) => {
      return response;
    });
  }

  editPassword(email: String, oldPassword: String, newPassword: String) {
    return this.authHttp.post('userapi/edit/password/', {
      email: email,
      oldPassword: oldPassword,
      newPassword: newPassword
    }).map((response: Response) => {
      return response;
    });
  }

  authorize(username: string, password: string) {
    const resp = this.http.post('userapi/authorize', {username: username, password: password}).map((response: Response) => {
      return response;
    });
    resp.subscribe((obj: any) => {
      this._isAuthenticatedSubject.next(true);
      const authObject: AuthObject = obj.json();
      localStorage.setItem('id_token', authObject.token);
      this.initializeWebSocketConnection();
    });
    return resp;
  }

  logout() {
    this._isAuthenticatedSubject.next(false);
    localStorage.removeItem('id_token');
    this.stompClient.disconnect();
    return this.http.post('userapi/logout', {}).map((response: Response) => {
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

  initAuth() {
    console.log('Checking authentication...');
    this.getCurrentUser().subscribe((obj: any) => {
      this._isAuthenticatedSubject.next(true);
      this.initializeWebSocketConnection();
      console.log('App is authenticated');
    }, (error: any) => {
      this._isAuthenticatedSubject.next(false);
      console.log('App is not authenticated');
    });
  }
}