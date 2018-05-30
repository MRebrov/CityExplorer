import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {OffersService} from '../offers.service';
import {OfferDTO} from '../offer.model';

@Component({
  selector: 'app-owned-offers',
  templateUrl: './owned-offers.component.html',
  styleUrls: ['./owned-offers.component.css']
})
export class OwnedOffersComponent implements OnInit {
  ownedOffers: OfferDTO[];
  loading: boolean = false;

  constructor(private router: Router, private offerService: OffersService) {

  }

  ngOnInit() {
    this.loading = true;
    this.offerService.getOwnedOffers().subscribe(
      (offers: any[]) => {
        this.ownedOffers = offers;
        this.loading = false;
        for (let offer of this.ownedOffers) {
          offer.expireDate = new Date(offer.expireDate);
        }
        this.ownedOffers.sort((a, b) => {
          if (a.expireDate > b.expireDate)
            return -1;
          else
            return 1;
        });
        console.log('Owned offers loaded successfully');
      },
      (error) => {
        console.log(error);
        this.loading = false;
        alert('Seems like you are not authorized! Please sign in first');
        this.router.navigate(['/login']);
      });
  }

}
