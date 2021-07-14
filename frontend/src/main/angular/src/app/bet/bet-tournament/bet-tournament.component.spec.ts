import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetTournamentComponent } from './bet-tournament.component';

describe('BetTournamentComponent', () => {
  let component: BetTournamentComponent;
  let fixture: ComponentFixture<BetTournamentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetTournamentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetTournamentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
