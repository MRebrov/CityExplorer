import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationsListComponent } from './confirmations-list.component';

describe('ConfirmationsListComponent', () => {
  let component: ConfirmationsListComponent;
  let fixture: ComponentFixture<ConfirmationsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
