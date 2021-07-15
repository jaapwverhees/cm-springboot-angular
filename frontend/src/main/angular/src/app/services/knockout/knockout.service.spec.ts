import { TestBed } from '@angular/core/testing';

import { KnockoutService } from './knockout.service';

describe('KnockoutService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KnockoutService = TestBed.get(KnockoutService);
    expect(service).toBeTruthy();
  });
});
