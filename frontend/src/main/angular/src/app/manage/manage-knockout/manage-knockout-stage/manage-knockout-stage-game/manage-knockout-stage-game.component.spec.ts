import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageKnockoutStageGameComponent } from './manage-knockout-stage-game.component';

describe('ManageKnockoutStageGameComponent', () => {
  let component: ManageKnockoutStageGameComponent;
  let fixture: ComponentFixture<ManageKnockoutStageGameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageKnockoutStageGameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageKnockoutStageGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
