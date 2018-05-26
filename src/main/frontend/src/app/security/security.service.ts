import {Injectable} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class SecurityService {

  private sessionId = new BehaviorSubject<User>(new User('', '', '', '', '', '', 50, null, 0));
  currentSession = this.sessionId.asObservable();

  constructor() {
  }

  changeUser(newUser: User) {
    this.sessionId.next(newUser);
  }

}
