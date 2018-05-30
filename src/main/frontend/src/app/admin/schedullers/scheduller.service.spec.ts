import { TestBed, inject } from '@angular/core/testing';

import { SchedullerService } from './scheduller.service';

describe('SchedullerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SchedullerService]
    });
  });

  it('should be created', inject([SchedullerService], (service: SchedullerService) => {
    expect(service).toBeTruthy();
  }));
});
