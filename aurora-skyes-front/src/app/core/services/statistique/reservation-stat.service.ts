import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ReservationStatService {

    private eventSource: EventSource | null = null;
    private url: string = 'http://localhost:8080/reservations/historique';

    constructor() {
    }

    startListening(volId: number): Observable<{ event: string, data: any }> {
        return new Observable(observer => {
            this.eventSource = new EventSource(`${this.url}/${volId}`);

            this.eventSource.onmessage = (event) => {
                observer.next({event: 'message', data: event.data});
            };

            this.eventSource.addEventListener('ReservationOK', (event: MessageEvent) => {
                observer.next({event: 'ReservationOK', data: JSON.parse(event.data)});
            });

            this.eventSource.addEventListener('ReservationError', (event: MessageEvent) => {
                observer.next({event: 'ReservationError', data: event.data});
            });

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
