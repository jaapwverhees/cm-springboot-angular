import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PickSportComponent } from './pick-sport.component';

describe('PickSportComponent', () => {
  let component: PickSportComponent;
  let fixture: ComponentFixture<PickSportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PickSportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PickSportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
