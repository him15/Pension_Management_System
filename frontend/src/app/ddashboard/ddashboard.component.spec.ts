import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DdashboardComponent } from './ddashboard.component';

describe('DdashboardComponent', () => {
  let component: DdashboardComponent;
  let fixture: ComponentFixture<DdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DdashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
