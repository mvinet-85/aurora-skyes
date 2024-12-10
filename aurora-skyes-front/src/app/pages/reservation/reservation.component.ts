import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Import pour la navigation
import { DatePipe, NgForOf, NgIf } from '@angular/common';

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
    NgIf,
    NgForOf
  ],
  styleUrls: ['./reservation.component.scss'],
})
export class ReservationComponent implements OnInit {
  reservationList: Reservation[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationList = [
      {
        nom: 'John Doe',
        vol: 'Paris - New York',
        date: new Date('2023-12-10'),
        passagers: 2,
        classe: 'Economy',
      },
      {
        nom: 'Jane Smith',
        vol: 'London - Tokyo',
        date: new Date('2023-12-15'),
        passagers: 1,
        classe: 'Business',
      },
    ];
  }
  navigateToHome(): void {
    this.router.navigate(['/home']).then(r => {});
  }
}
