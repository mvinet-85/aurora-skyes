import {vol} from './vol';

export interface reservation {
  id?: number;
  userId: number;
  volId: number;
  vol?: vol;
  classe: string;
  siege: string;
}
