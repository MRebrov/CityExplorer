import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {QuestDTO} from "./quest.model";
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

  getQuests(){
    return this.http.get('userapi/get-quests').map((response: Response) => {
      return response.json();
    });
  }

  postPhoto(file: File, quest: QuestDTO){
    let formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('quest', JSON.stringify(quest));
    return this.http.post('userapi/upload-photo', formdata).map(res => res.text());
  }

  postQuestInfo(quest: QuestDTO){
    return this.http.post('userapi/upload-info', quest).map(res=>res.text());
  }
  // loadQuestInfo(quest:QuestDTO){
  //   const req = new HttpRequest('POST', 'userapi/add-quest', quest, {
  //     responseType: 'text'
  //   });
  //   return this.http.request(req);
  // }

}
