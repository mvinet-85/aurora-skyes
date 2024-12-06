import {Component, inject} from '@angular/core';
import {AuthService} from "../../core/services/authentification/auth.service";

@Component({
    selector: 'app-header',
    imports: [],
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
