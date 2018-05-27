import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {AuthHttp} from 'angular2-jwt';
import {OfferDTO} from './offer.model';

@Injectable()
export class OffersService {
  constructor(private http: Http, private authHttp: AuthHttp) {

  }

  getOffers(amount: number, portion: number) {
    return this.http.get('userapi/get-offers/'+amount+'/'+portion).map((response: Response) =>{
      return response.json();
    });
  }

  getOffersByCategory(categoryId: number, amount: number, portion: number) {
    return this.http.get('userapi/get-offers-by-category/'+categoryId+'/'+amount+'/'+portion).map((response: Response) =>{
      return response.json();
    });
  }

  getMyOffers() {
    return this.authHttp.get('userapi/get-my-offers').map((response: Response) =>{
      return response.json();
    });
  }

  getOwnedOffers() {
    return this.authHttp.get('userapi/get-owned-offers').map((response: Response) =>{
      return response.json();
    });
  }

  getCategories() {
    return this.http.get('userapi/get-categories').map((response: Response) =>{
      return response.json();
    });
  }

  saveOffer(offerDTO: OfferDTO) {
    return this.authHttp.post('userapi/save-offer', offerDTO).map(res => res.text());
  }

  purchaseOffer(offerId: number) {
    return this.authHttp.post('userapi/purchase-offer/'+offerId, {}).map((response: Response) => {
      return response;
    });
  }
}
