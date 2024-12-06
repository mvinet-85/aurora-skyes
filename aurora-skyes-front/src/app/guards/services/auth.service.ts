import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  private userRole: string | null = null;

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
