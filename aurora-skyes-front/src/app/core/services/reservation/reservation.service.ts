import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {reservation} from '../../models/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly apiUrl = 'http://localhost:8080/reservations';

  constructor(private readonly http: HttpClient) {
  }

  save(reservation: reservation): Observable<reservation> {
    return this.http.post<reservation>(this.apiUrl, reservation);
  }

  reservationByUserId(userId: number): Observable<reservation[]> {
    return this.http.get<reservation[]>(`${this.apiUrl}/user/${userId}`);
  }
}
