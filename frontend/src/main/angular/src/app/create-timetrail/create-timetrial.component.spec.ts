import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTimetrialComponent } from './create-timetrial.component';

describe('CreateTimetrailComponent', () => {
  let component: CreateTimetrialComponent;
  let fixture: ComponentFixture<CreateTimetrialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTimetrialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTimetrialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
