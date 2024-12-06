import {inject, Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthService} from '../services/authentification/auth.service';
import {ToastService} from "../services/toast/toast.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    private readonly authService: AuthService = inject(AuthService);
    private readonly toastService: ToastService = inject(ToastService);
    private readonly router: Router = inject(Router);

    constructor() {
    }

    canActivate(): boolean {
        if (this.authService.isAuthenticated()) {
            return true;
        } else {
            this.toastService.showToast("Vous devez être connecté pour accéder à ces pages", "error");
            this.router.navigate(['/login']);
            return false;
        }
    }
}
