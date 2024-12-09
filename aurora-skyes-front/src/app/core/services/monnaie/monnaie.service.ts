import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {monnaie} from '../../models/monnaie';

@Injectable({
  providedIn: 'root'
})
export class MonnaieService {

  private readonly apiUrl = 'http://localhost:8080/monnaies';

  constructor(private readonly http: HttpClient) {
  }

  getAllCurrencies(): Observable<monnaie[]> {
    return this.http.get<monnaie[]>(this.apiUrl);
  }

  getPrice(tauxMonnaie: number, prixEuro: number): number {
    return prixEuro * tauxMonnaie;
  }
}
