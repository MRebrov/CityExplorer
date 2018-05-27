import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnedOffersComponent } from './owned-offers.component';

describe('OwnedOffersComponent', () => {
  let component: OwnedOffersComponent;
  let fixture: ComponentFixture<OwnedOffersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnedOffersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnedOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
