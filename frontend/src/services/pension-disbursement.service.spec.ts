import { TestBed } from '@angular/core/testing';

import { PensionDisbursementService } from './pension-disbursement.service';

describe('PensionDisbursementService', () => {
  let service: PensionDisbursementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PensionDisbursementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
