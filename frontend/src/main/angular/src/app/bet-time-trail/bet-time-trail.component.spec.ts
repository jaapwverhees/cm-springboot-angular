import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetTimeTrailComponent } from './bet-time-trail.component';

describe('BetTimeTrailComponent', () => {
  let component: BetTimeTrailComponent;
  let fixture: ComponentFixture<BetTimeTrailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetTimeTrailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetTimeTrailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
