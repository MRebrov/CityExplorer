import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Quest} from "./quest.model";
import {Http, Response} from '@angular/http';

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
    return this.http.post('/upload-photo', formdata);
  }

  postQuestInfo(quest: Quest){
    return this.http.post('/upload-info', quest);
  }
  // loadQuestInfo(quest:Quest){
  //   const req = new HttpRequest('POST', 'userapi/add-quest', quest, {
  //     responseType: 'text'
  //   });
  //   return this.http.request(req);
  // }

}
