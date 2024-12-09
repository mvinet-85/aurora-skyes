import { TestBed } from '@angular/core/testing';

import { MonnaieService } from './monnaie.service';

describe('MonnaieService', () => {
  let service: MonnaieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MonnaieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
