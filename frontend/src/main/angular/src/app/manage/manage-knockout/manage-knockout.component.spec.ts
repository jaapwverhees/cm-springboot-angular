import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageKnockoutComponent } from './manage-knockout.component';

describe('ManageKnockoutComponent', () => {
  let component: ManageKnockoutComponent;
  let fixture: ComponentFixture<ManageKnockoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageKnockoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageKnockoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
