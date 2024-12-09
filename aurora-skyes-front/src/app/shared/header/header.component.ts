import { Component, inject } from '@angular/core';
import { AuthService } from '../../core/services/authentification/auth.service';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-header',
  imports: [TranslatePipe],
  templateUrl: './header.component.html',
  standalone: true,
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  public authService: AuthService = inject(AuthService);
  private translateService: TranslateService = inject(TranslateService);

  deconnexion() {
    this.authService.logout();
  }

  changeLanguage(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const selectedLanguage = selectElement.value;
    this.translateService.use(selectedLanguage);
  }
  
}
