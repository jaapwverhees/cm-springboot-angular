import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateKnockoutComponent } from './create-knockout.component';

describe('CreateKnockoutComponent', () => {
  let component: CreateKnockoutComponent;
  let fixture: ComponentFixture<CreateKnockoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateKnockoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateKnockoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
