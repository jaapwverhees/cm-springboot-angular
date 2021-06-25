import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PickCompetitionComponent } from './pick-competition.component';

describe('PickCompetitionComponent', () => {
  let component: PickCompetitionComponent;
  let fixture: ComponentFixture<PickCompetitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PickCompetitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PickCompetitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
