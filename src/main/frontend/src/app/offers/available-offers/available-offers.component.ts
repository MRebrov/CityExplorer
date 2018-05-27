import {Component, OnInit} from '@angular/core';
import {OffersService} from '../offers.service';
import {OfferDTO} from '../offer.model';
import {Router} from '@angular/router';
import {OfferCategoryDTO} from '../offer-category.model';
import {Observable} from 'rxjs/Observable';
import {UserService} from '../../user/user.service';

@Component({
  selector: 'app-available-offers',
  templateUrl: './available-offers.component.html',
  styleUrls: ['./available-offers.component.css']
})
export class AvailableOffersComponent implements OnInit {
  errorMsg: string;
  availableOffers: OfferDTO[];
  categories: OfferCategoryDTO[] = [];
  page: number = 1;
  loading: boolean = false;
  itemsPerPage: number = 10;
  selectedCategory: number = -1;
  authUser = null;

  constructor(private userService: UserService, private router: Router, private offerService: OffersService) {
  }

  ngOnInit() {
    this.loadOffers();
    this.offerService.getCategories().subscribe((list: any[]) => {
      this.categories = list;
    });
    this.userService.getCurrentUser().subscribe(
      (user: any) => {
        this.authUser = user;
      });
  }

  loadOffers() {
    let successHandler = (offers: any[]) => {
      this.loading = false;

      if (offers.length == 0 && this.page > 1) {
        this.page--;
        return;
      }

      this.availableOffers = offers;
      for (let offer of this.availableOffers) {
        offer.expireDate = new Date(offer.expireDate);
      }
      this.availableOffers.sort((a, b) => {
        if (a.expireDate > b.expireDate)
          return -1;
        else
          return 1;
      });
      console.log('Owned offers loaded successfully');
    };

    let errorHandler = (error) => {
      console.log(error);
      this.loading = false;
      alert('Seems like you are not authorized! Please sign in first');
      this.router.navigate(['/login']);
    };

    this.loading = true;
    if (this.selectedCategory == -1) {
      this.offerService.getOffers(this.itemsPerPage, this.page - 1).subscribe(successHandler, errorHandler);
    }
    else {
      this.offerService.getOffersByCategory(this.selectedCategory, this.itemsPerPage, this.page - 1).subscribe(successHandler, errorHandler);
    }
  }


  prevPage() {
    if (!this.loading && this.page > 1) {
      this.page--;
      this.loadOffers();
    }
  }

  nextPage() {
    if (!this.loading) {
      this.page++;
      this.loadOffers();
    }
  }

  onItemChanged(value) {
    if (this.selectedCategory != value) {
      this.selectedCategory = value;
      this.page = 1;
      this.loadOffers();
    }
  }

  purchaseClick(offerId: number) {
    this.loading = true;
    this.offerService.purchaseOffer(offerId).catch((response: Response) => {

      this.writeError(response.text());
      this.loading = false;
      window.scrollTo(0, 0);
      return Observable.throw(response);
    })
      .subscribe(
        (response: any) => {
          console.log(response);
          this.loading = false;
          this.writeError("Offer purchased successfully");
          this.authUser.balance=this.authUser.balance-this.availableOffers.find((a)=>{return a.id==offerId}).price;
          window.scrollTo(0, 0);
        });
  }

  writeError(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

}
