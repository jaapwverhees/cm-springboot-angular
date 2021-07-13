import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageTimeTrialComponent } from './manage-time-trial.component';

describe('ManageTimeTrailComponent', () => {
  let component: ManageTimeTrialComponent;
  let fixture: ComponentFixture<ManageTimeTrialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageTimeTrialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageTimeTrialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
