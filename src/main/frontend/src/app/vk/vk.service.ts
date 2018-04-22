import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";
import * as ApiProvider from "vk-api-angular";

@Injectable()
export class VkService {

  private APP_ID: number = 6346913;
  private CLIENT_SECRET: string = "KSyvMNJDmCkP7bg6K1ms";


  constructor(private http: Http) {
  }

  login() {
    window.location.href = "https://oauth.vk.com/authorize?client_id="+this.APP_ID+"&display=page&redirect_uri=http://localhost:8081/vk&scope=friends,wall,photos&response_type=token&v=5.71&state=12345";
  }
  sendCode(code:string){
    return this.http.get("/userapi/signin/"+code).map((response:Response) =>{
      return response.json();
    });
  }


}
