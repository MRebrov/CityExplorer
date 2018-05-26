import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableOffersComponent } from './available-offers.component';

describe('AvailableOffersComponent', () => {
  let component: AvailableOffersComponent;
  let fixture: ComponentFixture<AvailableOffersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvailableOffersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
