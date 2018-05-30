import { Injectable } from '@angular/core';
import {AuthHttp} from "angular2-jwt";
import {TaskDTO} from "../task.model";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Http, Response} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class SchedullerService {

  constructor(public authHttp: AuthHttp) { }

  sendTask(task: TaskDTO){
    return this.authHttp.post('userapi/add/task/', task).map((response: Response) =>{
      return response.text();
    })
  }

  getAllTasks(){
    return this.authHttp.get('userapi/getTasks').map((response: Response) =>{
      return response.json();
    })
  }
}
