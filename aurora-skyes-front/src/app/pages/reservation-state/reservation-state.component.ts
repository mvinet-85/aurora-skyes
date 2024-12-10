import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {HeaderComponent} from '../../shared/header/header.component';
import {ReservationStatService} from '../../core/services/statistique/reservation-stat.service';
import {reservationStat} from '../../core/models/reservation-stat';
import {Subscription} from 'rxjs';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-reservation-state',
  imports: [
    HeaderComponent,
    DatePipe
  ],
  templateUrl: './reservation-state.component.html',
  standalone: true,
  styleUrl: './reservation-state.component.scss'
})
export class ReservationStateComponent implements OnInit, OnDestroy {

  public reservationStats: reservationStat | null = null;
  private reservationStatService: ReservationStatService = inject(ReservationStatService);
  private subscription: Subscription = inject(Subscription);

  ngOnInit(): void {
    this.subscription = this.reservationStatService.startListening().subscribe(
      (data: reservationStat) => {
        this.reservationStats = data;
      },
      (error) => {
        console.error('Erreur SSE:', error);
      }
    );
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
