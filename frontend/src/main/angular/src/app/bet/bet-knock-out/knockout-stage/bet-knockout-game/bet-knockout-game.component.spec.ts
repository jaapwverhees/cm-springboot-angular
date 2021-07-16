import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetKnockoutGameComponent } from './bet-knockout-game.component';

describe('BetKnockoutGameComponent', () => {
  let component: BetKnockoutGameComponent;
  let fixture: ComponentFixture<BetKnockoutGameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetKnockoutGameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetKnockoutGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
