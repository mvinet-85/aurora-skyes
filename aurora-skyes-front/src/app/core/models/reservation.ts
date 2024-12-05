import {flight} from './flight';

export interface reservation {
  vol: flight;
  user: string;
  prix: number;
  classe: string;
  siege: string;
  reservationDate: Date;
}
