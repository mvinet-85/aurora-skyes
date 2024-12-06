import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {utilisateur} from '../models/user';
import {utilisateurLogin} from '../models/login';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private isAuthenticated = false;
    private userRole: string | null = null;

    private readonly registerUrl = 'http://localhost:8080/utilisateurs';
    private readonly loginUrl = 'http://localhost:8080/authentification';
    private readonly http: HttpClient = inject(HttpClient);
    private readonly router: Router = inject(Router);

    constructor() {
    }

    signUp(utilisateur: utilisateur): Observable<utilisateur> {
        return this.http.post<utilisateur>(this.registerUrl, utilisateur);
    }

    signIn(utilisateur: utilisateurLogin): Observable<utilisateur> {
        return this.http.post<utilisateur>(this.loginUrl, utilisateur).pipe(
            tap(() => {
                this.isAuthenticated = true;
                console.log('Utilisateur authentifié');
                this.router.navigateByUrl('/home');
            })
        );
    }

    login(role: string): void {
        this.isAuthenticated = true;
        this.userRole = role;
    }

    logout(): void {
        this.isAuthenticated = false;
        this.userRole = null;
    }

    isLoggedIn(): boolean {
        return this.isAuthenticated;
    }

    getUserRole(): string | null {
        return this.userRole;
    }
}
