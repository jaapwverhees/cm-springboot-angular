import { TestBed } from '@angular/core/testing';

import { TournamentService } from './tournament.service';

describe('ChampionshipService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TournamentService = TestBed.get(TournamentService);
    expect(service).toBeTruthy();
  });
});
