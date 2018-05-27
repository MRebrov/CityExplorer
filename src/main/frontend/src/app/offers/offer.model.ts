import {OfferCategoryDTO} from './offer-category.model';

export class OfferDTO {
  public id: number;
  public name: string;
  public category: OfferCategoryDTO;
  public ownerName: string;
  public amount: number;
  public expireDate: Date;
  public photoURL: string;
  public price: number;

  constructor() {

  }
}
