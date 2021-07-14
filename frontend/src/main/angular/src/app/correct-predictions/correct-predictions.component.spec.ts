import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorrectPredictionsComponent } from './correct-predictions.component';

describe('CorrectPredictionsComponent', () => {
  let component: CorrectPredictionsComponent;
  let fixture: ComponentFixture<CorrectPredictionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorrectPredictionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorrectPredictionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
