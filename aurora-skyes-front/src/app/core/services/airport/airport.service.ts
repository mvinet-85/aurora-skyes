import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {airport} from '../../models/airport';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  private readonly apiUrl = 'http://localhost:8080/aeroports';

  constructor(private readonly http: HttpClient) {
  }

  getAllAirports(): Observable<airport[]> {
    return this.http.get<airport[]>(this.apiUrl);
  }
}
