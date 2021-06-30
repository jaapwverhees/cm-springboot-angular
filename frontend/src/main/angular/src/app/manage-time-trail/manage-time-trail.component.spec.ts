import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageTimeTrailComponent } from './manage-time-trail.component';

describe('ManageTimeTrailComponent', () => {
  let component: ManageTimeTrailComponent;
  let fixture: ComponentFixture<ManageTimeTrailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageTimeTrailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageTimeTrailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
