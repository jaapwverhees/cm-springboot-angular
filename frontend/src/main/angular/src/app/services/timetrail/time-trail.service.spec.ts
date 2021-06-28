import { TestBed } from '@angular/core/testing';

import { TimeTrailService } from './time-trail.service';

describe('TimeTrailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TimeTrailService = TestBed.get(TimeTrailService);
    expect(service).toBeTruthy();
  });
});
