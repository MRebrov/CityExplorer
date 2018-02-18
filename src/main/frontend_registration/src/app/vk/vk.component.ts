import { Component, OnInit } from '@angular/core';
import {VkService} from "./vk.service";
import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Params} from "@angular/router";
import {VkUser} from "./vkuser.model";

@Component({
  selector: 'app-vk',
  templateUrl: './vk.component.html',
  styleUrls: ['./vk.component.css'],
  providers: [VkService]
})
export class VkComponent implements OnInit {

  constructor(private vkService: VkService, private route: ActivatedRoute) {

  }
  code:string;
  userId:string;
  vkuser: VkUser = new VkUser('','','','');
  ngOnInit() {
    this.route.fragment.subscribe((fragment: string) =>{
      this.code = fragment;
      //this.userId = fragments['user_id']
    });
    let params = this.code.split("&");
    this.code = params[0].split("=")[1];
    this.userId = params[2].split("=")[1];

    if (this.code != null){
      this.vkService.sendCode(this.code+'~'+this.userId).subscribe(
        (vkuser: VkUser) => {
          this.vkuser = vkuser;
        },
        (error) => console.log(error)
      );
    }
  }

  onVkLogin(){
    this.vkService.login();
  }


}
