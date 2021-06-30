import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeTrailScoreComponent } from './time-trail-score.component';

describe('TimeTrailScoreComponent', () => {
  let component: TimeTrailScoreComponent;
  let fixture: ComponentFixture<TimeTrailScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TimeTrailScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimeTrailScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
