import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvinceRowCardComponent } from './province-row-card.component';

describe('ProvinceRowCardComponent', () => {
  let component: ProvinceRowCardComponent;
  let fixture: ComponentFixture<ProvinceRowCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProvinceRowCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvinceRowCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
