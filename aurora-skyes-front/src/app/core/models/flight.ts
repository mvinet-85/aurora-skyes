import {airport} from './airport';

export interface flight {
  flightNumber: number;
  dateDepart: Date;
  dateArrive: Date;
  aeroportDepart: airport;
  aeroportArrivee: airport;
  placeDisponible: number;
  prix: number;
}
