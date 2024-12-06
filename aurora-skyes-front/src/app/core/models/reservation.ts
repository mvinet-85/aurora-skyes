import {flight} from './flight';
import {utilisateur} from './user';

export interface reservation {
  vol: flight;
  utilisateur: utilisateur;
  prix: number;
  classe: string;
  siege: string;
}
