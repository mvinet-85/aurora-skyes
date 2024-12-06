import {aeroport} from './aeroport';

export interface flight {
  id: number;
  dateDepart: Date;
  dateArrive: Date;
  aeroportDepart: aeroport;
  aeroportArrivee: aeroport;
  placeDisponible: number;
  prix: number;
}
