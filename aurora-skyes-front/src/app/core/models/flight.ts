import {airport} from './airport';

export interface flight {
  // flightNumber: string;
  dateDepart: Date;
  dateArrive: Date;
  aeroportDepart: airport;
  aeroportArrivee: airport;
}
