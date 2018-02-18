import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPageComponentComponent } from './user-page-component.component';

describe('UserPageComponentComponent', () => {
  let component: UserPageComponentComponent;
  let fixture: ComponentFixture<UserPageComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPageComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPageComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
