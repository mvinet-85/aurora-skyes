import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationStateComponent } from './reservation-state.component';

describe('ReservationStateComponent', () => {
  let component: ReservationStateComponent;
  let fixture: ComponentFixture<ReservationStateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservationStateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationStateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
