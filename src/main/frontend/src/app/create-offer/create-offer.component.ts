import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PasswordValidation} from '../registration/password.validation';
import {OfferDTO} from '../offers/offer.model';
import {OfferCategoryDTO} from '../offers/offer-category.model';
import {OffersService} from '../offers/offers.service';
import {UserService} from '../user/user.service';
import {QuestService} from '../quest/quest.service';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-offer',
  templateUrl: './create-offer.component.html',
  styleUrls: ['./create-offer.component.css']
})
export class CreateOfferComponent implements OnInit {
  errorMsg: string;
  fileName = 'Choose photo...';
  offer: OfferDTO = new OfferDTO();
  categories: OfferCategoryDTO[] = [];
  balance: number;
  loading: boolean = false;

  constructor(private router: Router, private offerService: OffersService, private userService: UserService, private fb: FormBuilder, private questService: QuestService) {
    this.offer.amount = 0;
    this.offer.price = 0;
  }

  ngOnInit() {
    this.offerService.getCategories().subscribe((list: any[]) => {
      this.categories = list;
    });
    this.userService.getCurrentUser().subscribe(
      (user: any) => {
        this.balance = user.businessBalance;
      }, (error) => {
        console.log(error);
        alert('You are not authorized! Please sign in first');
        this.router.navigate(['/login']);
      });
  }

  submitForm() {
    if (this.offer.name == null || this.offer.name.length == 0) {
      this.writeError('Please write some text for an offer');
      return;
    }
    if (this.offer.price == null) {
      this.writeError('Please specify offer price');
      return;
    }
    if (this.offer.category == null) {
      this.writeError('Please specify category');
      return;
    }
    if (this.balance < this.offer.price) {
      this.writeError('Not enough business cash to create offer');
      return;
    }
    if (this.offer.photoURL == null) {
      this.writeError('Please choose some photo');
      return;
    }
    if (this.offer.expireDate == null) {
      this.writeError('Please specify offer expire date');
      return;
    }

    this.loading = true;
    this.offerService.saveOffer(this.offer).subscribe((data: any) => {
        console.log(data);
        this.loading = false;
        this.writeError('Offer created successfully');
        this.offer = new OfferDTO();
      },
      (error) => {
        this.loading = false;
        this.writeError(error);
      });
  }

  selectFile(event) {
    this.loading = true;
    this.questService.uploadSpotPhoto(event.target.files[0]).catch((response) => {
      return Observable.throw(response);
    }).subscribe((data) => {
        console.log(data);
        this.loading = false;
        this.offer.photoURL = data;
        this.fileName = event.target.files[0].name;
      },
      (error) => {
        console.log(error);
        this.loading = false;
      });
  }

  writeError(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

  onItemChanged(value) {
    this.offer.category = this.categories.find((a) => {
      return a.id == value;
    });
  }

  getCreationCost() {
    return this.offer.amount * this.offer.price;
  }

}
