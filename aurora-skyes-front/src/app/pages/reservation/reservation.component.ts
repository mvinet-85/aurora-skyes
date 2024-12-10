import {Component, inject, OnInit} from '@angular/core';
import {DatePipe} from '@angular/common';
import {reservation} from '../../core/models/reservation';
import {ReservationService} from '../../core/services/reservation/reservation.service';
import {ToastService} from '../../core/services/toast/toast.service';
import {AuthService} from '../../core/services/authentification/auth.service';
import {Router} from '@angular/router';
import {HeaderComponent} from '../../shared/header/header.component';

interface Reservation {
  nom: string;
  vol: string;
  date: Date;
  passagers: number;
  classe: string;
}

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  standalone: true,
  imports: [
    DatePipe,
    HeaderComponent,
  ],
  styleUrls: ['./reservation.component.scss'],
})
export class ReservationComponent implements OnInit {

  public reservationList: reservation[] = [];
  public userId: number | undefined = undefined;

  private readonly reservationService: ReservationService = inject(ReservationService);
  private readonly toastService: ToastService = inject(ToastService);
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);

  ngOnInit(): void {
    this.userId = this.authService.getUser()?.id;
    this.getUserReservation();
  }

  navigateToHome(): void {
    this.router.navigate(['/home']).then(r => {
    });
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

