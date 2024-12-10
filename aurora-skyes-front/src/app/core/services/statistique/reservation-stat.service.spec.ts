import { TestBed } from '@angular/core/testing';

import { ReservationStatService } from './reservation-stat.service';

describe('ReservationStatService', () => {
  let service: ReservationStatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationStatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
