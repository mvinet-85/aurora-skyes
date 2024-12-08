import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {utilisateur} from '../../models/utilisateur';
import {utilisateurLogin} from '../../models/authentification';
import {Router} from '@angular/router';
import {ToastService} from '../toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly registerUrl = 'http://localhost:8080/utilisateurs';
  private readonly loginUrl = 'http://localhost:8080/authentification';
  private readonly http: HttpClient = inject(HttpClient);
  private readonly router: Router = inject(Router);
  private readonly toastService: ToastService = inject(ToastService);

  private currentUser: utilisateur | null = null;

  constructor() {
    const user = localStorage.getItem('currentUser');
    if (user) {
      this.currentUser = JSON.parse(user);
    }
  }

  signUp(utilisateur: utilisateur): Observable<utilisateur> {
    return this.http.post<utilisateur>(this.registerUrl, utilisateur).pipe(
      tap((user: utilisateur) => {
        this.currentUser = user;
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigateByUrl('/home');
      })
    );
  }

  signIn(utilisateur: utilisateurLogin): Observable<utilisateur> {
    return this.http.post<utilisateur>(this.loginUrl, utilisateur).pipe(
      tap((user: utilisateur) => {
        this.currentUser = user;
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigateByUrl('/home');
      })
    );
  }

  public isAuthenticated(): boolean {
    return this.currentUser !== null;
  }

  getUser(): utilisateur | null {
    return this.currentUser;
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
    this.toastService.showToast('Vous êtes déconnecté', 'info')
  }
}
