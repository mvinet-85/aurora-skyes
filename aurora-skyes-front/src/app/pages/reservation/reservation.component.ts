import {Component, inject, OnInit} from '@angular/core';
import {HeaderComponent} from '../../shared/header/header.component';
import {ReservationService} from '../../core/services/reservation/reservation.service';
import {reservation} from '../../core/models/reservation';
import {ToastService} from '../../core/services/toast/toast.service';
import {AuthService} from '../../core/services/authentification/auth.service';

@Component({
  selector: 'app-reservation',
  imports: [
    HeaderComponent
  ],
  templateUrl: './reservation.component.html',
  standalone: true,
  styleUrl: './reservation.component.scss'
})
export class ReservationComponent implements OnInit {

  public reservationList: reservation[] = [];
  public userId: number | undefined = undefined;

  private readonly reservationService: ReservationService = inject(ReservationService);
  private readonly toastService: ToastService = inject(ToastService);
  private readonly authService: AuthService = inject(AuthService);

  ngOnInit(): void {
    this.userId = this.authService.getUser()?.id;
    this.getUserReservation();
  }

  private getUserReservation(): void {
    if (this.userId) {
      this.reservationService.reservationByUserId(this.userId).subscribe(
        (data: reservation[]) => {
          this.reservationList = data;
        },
        (error) => {
          console.error('Erreur lors de la récupération des réservation', error);
          this.toastService.showToast('Erreur lors de la récupération des réservation', 'error')
        }
      );
    }
  }
}
