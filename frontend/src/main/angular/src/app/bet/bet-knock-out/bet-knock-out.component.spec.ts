import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetKnockOutComponent } from './bet-knock-out.component';

describe('BetKnockOutComponent', () => {
  let component: BetKnockOutComponent;
  let fixture: ComponentFixture<BetKnockOutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetKnockOutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetKnockOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
