import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {vol} from '../../models/vol';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  private readonly apiUrl = 'http://localhost:8080/vols';

  constructor(private readonly http: HttpClient) {
  }

  getAllVols(): Observable<vol[]> {
    return this.http.get<vol[]>(this.apiUrl);
  }

  getVolById(id: number): Observable<vol> {
    return this.http.get<vol>(`${this.apiUrl}/${id}`);
  }
}
