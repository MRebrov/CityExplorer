import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Quest} from "./quest.model";

@Injectable()
export class QuestService {

  constructor(private http: HttpClient) { }

  loadFile(file: File):Observable<HttpEvent<{}>>{
    let formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', '/upload-photo', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  loadQuestInfo(quest:Quest){
    const req = new HttpRequest('POST', 'userapi/add-quest', quest, {
      responseType: 'text'
    });
    return this.http.request(req);
  }

}
