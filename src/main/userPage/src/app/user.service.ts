import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import {User} from "./user.model";
import {EventEmitter, Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  getUser(email: string) {
    return this.http.get('userapi/get/byEmail/' + email).map(res=>res.json);
  }

}
