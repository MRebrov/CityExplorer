import { Component, OnInit } from '@angular/core';
import {OffersService} from '../offers.service';

@Component({
  selector: 'app-available-offers',
  templateUrl: './available-offers.component.html',
  styleUrls: ['./available-offers.component.css']
})
export class AvailableOffersComponent implements OnInit {

  constructor(private offerService: OffersService) { }

  ngOnInit() {
  }

}
