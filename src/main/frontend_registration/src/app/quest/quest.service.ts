import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Quest} from "./quest.model";
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class QuestService {

  constructor(private http: Http) { }
  //
  // loadFile(file: File):Observable<HttpEvent<{}>>{
  //   let formdata: FormData = new FormData();
  //
  //   formdata.append('file', file);
  //
  //   const req = new HttpRequest('POST', '/upload-photo', formdata, {
  //     reportProgress: true,
  //     responseType: 'text'
  //   });
  //
  //   return this.http.request(req);
  // }

  postPhoto(file: File){
    let formdata: FormData = new FormData();

    formdata.append('file', file);
    return this.http.post('userapi/upload-photo', formdata).map(res => res.text());
  }

  postQuestInfo(quest: Quest){
    return this.http.post('userapi/upload-info', quest).map(res=>res.text());
  }
  // loadQuestInfo(quest:Quest){
  //   const req = new HttpRequest('POST', 'userapi/add-quest', quest, {
  //     responseType: 'text'
  //   });
  //   return this.http.request(req);
  // }

}
