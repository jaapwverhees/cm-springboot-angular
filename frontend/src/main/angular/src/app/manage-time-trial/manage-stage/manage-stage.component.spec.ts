import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageStageComponent } from './manage-stage.component';

describe('ManageStageComponent', () => {
  let component: ManageStageComponent;
  let fixture: ComponentFixture<ManageStageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageStageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageStageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
