import { Component, OnInit } from '@angular/core';
import {VkService} from "./vk.service";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-vk',
  templateUrl: './vk.component.html',
  styleUrls: ['./vk.component.css'],
  providers: [VkService]
})
export class VkComponent implements OnInit {

  constructor(private vkService: VkService) { }

  ngOnInit() {
  }

  onVkLogin(event){
    this.vkService.login();
  }

}
