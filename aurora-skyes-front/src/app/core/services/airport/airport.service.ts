import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {aeroport} from '../../models/aeroport';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  private readonly apiUrl = 'http://localhost:8080/aeroports';

  constructor(private readonly http: HttpClient) {
  }

  getAllAirports(): Observable<aeroport[]> {
    return this.http.get<aeroport[]>(this.apiUrl);
  }
}
