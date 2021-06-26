import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensionDisbursementModuleComponent } from './authorization.component';

describe('PensionDisbursementModuleComponent', () => {
  let component: PensionDisbursementModuleComponent;
  let fixture: ComponentFixture<PensionDisbursementModuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionDisbursementModuleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionDisbursementModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
