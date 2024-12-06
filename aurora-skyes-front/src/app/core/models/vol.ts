import {aeroport} from './aeroport';

export interface vol {
  id: number;
  dateDepart: Date;
  dateArrive: Date;
  aeroportDepart: aeroport;
  aeroportArrivee: aeroport;
  placeDisponible: number;
  prix: number;
}
