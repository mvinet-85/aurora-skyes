import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {flight} from '../../models/flight';

@Injectable({
    providedIn: 'root'
})
export class FlightService {

    private readonly apiUrl = 'http://localhost:8080/vols';

    constructor(private readonly http: HttpClient) {
    }

    getAllVols(): Observable<flight[]> {
        return this.http.get<flight[]>(this.apiUrl);
    }
}
