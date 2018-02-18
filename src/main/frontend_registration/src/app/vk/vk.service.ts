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
   this.http.get("/signin");
  }

}
