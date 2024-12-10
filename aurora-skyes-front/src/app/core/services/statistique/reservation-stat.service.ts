import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {reservationStat} from '../../models/reservation-stat';

@Injectable({
    providedIn: 'root'
})
export class ReservationStatService {

    private eventSource: EventSource | null = null;
    private url: string = 'http://localhost:8080/reservations/historique';

    constructor() {
    }

    startListening(): Observable<reservationStat> {
        return new Observable(observer => {
            this.eventSource = new EventSource(this.url);

            this.eventSource.onmessage = (event) => {
                try {
                    const data: reservationStat = JSON.parse(event.data);
                    observer.next(data);
                } catch (error) {
                    observer.error('Erreur lors du parsing des données SSE');
                }
            };

            this.eventSource.onerror = (error) => {
                observer.error(error);
                this.closeConnection();
            };

            return () => {
                this.closeConnection();
            };
        });
    }

    private closeConnection(): void {
        if (this.eventSource) {
            this.eventSource.close();
        }
    }
}
