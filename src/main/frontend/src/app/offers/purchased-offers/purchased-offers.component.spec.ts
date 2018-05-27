import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasedOffersComponent } from './purchased-offers.component';

describe('PurchasedOffersComponent', () => {
  let component: PurchasedOffersComponent;
  let fixture: ComponentFixture<PurchasedOffersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PurchasedOffersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchasedOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
