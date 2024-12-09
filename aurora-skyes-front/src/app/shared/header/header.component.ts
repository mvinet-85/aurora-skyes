import {Component, inject} from '@angular/core';
import {AuthService} from "../../core/services/authentification/auth.service";
import {TranslatePipe} from '@ngx-translate/core';

@Component({
  selector: 'app-header',
  imports: [
    TranslatePipe
  ],
  templateUrl: './header.component.html',
  standalone: true,
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  public authService: AuthService = inject(AuthService);

  deconnexion() {
    this.authService.logout();
  }
}
