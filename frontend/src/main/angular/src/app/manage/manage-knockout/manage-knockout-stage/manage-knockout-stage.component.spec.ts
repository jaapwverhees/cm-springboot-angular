import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageKnockoutStageComponent } from './manage-knockout-stage.component';

describe('ManageKnockoutStageComponent', () => {
  let component: ManageKnockoutStageComponent;
  let fixture: ComponentFixture<ManageKnockoutStageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageKnockoutStageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageKnockoutStageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
