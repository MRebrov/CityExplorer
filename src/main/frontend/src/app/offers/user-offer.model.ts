import {OfferDTO} from './offer.model';
import {User} from '../user/user.model';

export class UserOfferDTO {
  public id: number;
  public  user: User;
  public  offer: OfferDTO;

  constructor() {

  }
}
