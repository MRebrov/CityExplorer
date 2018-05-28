import { Component, OnInit } from '@angular/core';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {

  constructor(public userService:UserService) { }

  ngOnInit() {
  }

}
