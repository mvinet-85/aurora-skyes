import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {
  private translations: any = {};
  private currentLanguage: string = 'fr';

  constructor(private http: HttpClient) {
  }

  loadTranslations(lang: string): Promise<void> {
    return this.http
      .get(`/assets/i18n/${lang}.json`)
      .toPromise()
      .then((data) => {
        this.translations = data;
        this.currentLanguage = lang;
      });
  }

  translate(key: string): string {
    return this.translations[key] || key;
  }

  changeLanguage(lang: string): Promise<void> {
    return this.loadTranslations(lang);
  }
}
