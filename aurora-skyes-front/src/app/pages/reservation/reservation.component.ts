import {Component} from '@angular/core';
import {HeaderComponent} from '../../shared/header/header.component';

@Component({
  selector: 'app-reservation',
  imports: [
    HeaderComponent
  ],
  templateUrl: './reservation.component.html',
  standalone: true,
  styleUrl: './reservation.component.scss'
})
export class ReservationComponent {

}
