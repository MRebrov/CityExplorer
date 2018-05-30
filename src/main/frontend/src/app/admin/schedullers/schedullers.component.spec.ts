import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedullersComponent } from './schedullers.component';

describe('SchedullersComponent', () => {
  let component: SchedullersComponent;
  let fixture: ComponentFixture<SchedullersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedullersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedullersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
