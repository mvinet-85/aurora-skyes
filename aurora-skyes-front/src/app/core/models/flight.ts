import {airport} from './airport';

export interface flight {
  id: number;
  dateDepart: Date;
  dateArrive: Date;
  aeroportDepart: airport;
  aeroportArrivee: airport;
  placeDisponible: number;
  prix: number;
}
