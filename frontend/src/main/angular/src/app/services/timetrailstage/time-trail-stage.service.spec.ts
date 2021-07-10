import { TestBed } from '@angular/core/testing';

import { TimeTrailStageService } from './time-trail-stage.service';

describe('TimeTrailStageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TimeTrailStageService = TestBed.get(TimeTrailStageService);
    expect(service).toBeTruthy();
  });
});
