import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTimetrailComponent } from './create-timetrail.component';

describe('CreateTimetrailComponent', () => {
  let component: CreateTimetrailComponent;
  let fixture: ComponentFixture<CreateTimetrailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTimetrailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTimetrailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
