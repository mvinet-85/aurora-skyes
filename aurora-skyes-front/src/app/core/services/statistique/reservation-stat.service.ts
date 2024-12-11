import {Injectable} from '@angular/core';
import {interval, map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationStatService {
  private readonly eventSourceReservationOK: EventSource | null = null;
  private readonly eventSourceReservationError: EventSource | null = null;
  private readonly urlReservationOK: string = 'http://localhost:8080/reservations/historique';
  private readonly urlReservationError: string = 'http://localhost:8080/reservations/historique/error';

  constructor() {
  }

  startListening(volId: number): Observable<{ event: string, data: any }> {
    // return new Observable((observer: Observer<{ event: string, data: any }>) => {
    //   this.eventSourceReservationOK = new EventSource(`${this.urlReservationOK}/${volId}`);
    //
    //   this.eventSourceReservationOK.onmessage = (event) => {
    //     observer.next({event: 'ReservationOK', data: event.data});
    //   };
    //
    //   this.eventSourceReservationError = new EventSource(`${this.urlReservationError}/${volId}`);
    //   this.eventSourceReservationError.onmessage = (event) => {
    //     observer.next({event: 'ReservationError', data: event.data});
    //   };
    //
    //   this.eventSourceReservationOK.onerror = (error) => {
    //     observer.error(error);
    //     this.closeConnection();
    //   };
    //   this.eventSourceReservationError.onerror = (error) => {
    //     observer.error(error);
    //     this.closeConnection();
    //   };
    // });
    return interval(6000).pipe(
      map(() => {
        const isOK = Math.random() > 0.5;
        return {
          event: isOK ? 'ReservationOK' : 'ReservationError',
          data: isOK ? Math.floor(Math.random() * 100) + 1 : 1
        };
      })
    );
  }

  private closeConnection(): void {
    if (this.eventSourceReservationOK) {
      this.eventSourceReservationOK.close();
    }
    if (this.eventSourceReservationError) {
      this.eventSourceReservationError.close();
    }
  }
}
