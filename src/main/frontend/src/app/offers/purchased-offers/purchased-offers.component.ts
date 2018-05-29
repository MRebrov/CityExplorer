import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {OffersService} from '../offers.service';
import {OfferDTO} from '../offer.model';
import {UserOfferDTO} from '../user-offer.model';

@Component({
  selector: 'app-purchased-offers',
  templateUrl: './purchased-offers.component.html',
  styleUrls: ['./purchased-offers.component.css']
})
export class PurchasedOffersComponent implements OnInit {


  purchasedOffers: UserOfferDTO[];
  public date: Date;

  constructor(private router: Router, private offerService: OffersService) {
    this.date=new Date();
    this.date.setHours(0,0,0,0);
  }

  ngOnInit() {
    this.loadOffers();
    document.getElementById("nav-profile-tab").addEventListener("click",()=>{
      this.loadOffers();
    });
  }

  loadOffers(){
    this.offerService.getMyOffers().subscribe(
      (offers: any[]) => {
        this.purchasedOffers = offers;
        for (let userOffer of this.purchasedOffers) {
          userOffer.offer.expireDate = new Date(userOffer.offer.expireDate);
        }
        this.purchasedOffers.sort((a, b) => {
          if (a.offer.expireDate > b.offer.expireDate)
            return -1;
          else
            return 1;
        });
        console.log('Purchased offers loaded successfully');
      },
      (error) => {
        console.log(error);
        alert('Seems like you are not authorized! Please sign in first');
        this.router.navigate(['/login']);
      });
  }
}
