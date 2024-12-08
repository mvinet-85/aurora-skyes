import {Component, inject, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../core/services/authentification/auth.service';
import {utilisateur} from '../../../core/models/utilisateur';
import {ToastService} from '../../../core/services/toast/toast.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  username: string = '';

  private readonly auth: AuthService = inject(AuthService);
  private readonly toastService: ToastService = inject(ToastService);
  private readonly router: Router = inject(Router);

  ngOnInit(): void {
  }

  register() {
    if (this.username === '') {
      alert('Please enter a username');
      return;
    }
    if (this.email === '') {
      alert('Please enter an email');
      return;
    }
    if (this.password === '') {
      alert('Please enter a password');
      return;
    }
    if (this.confirmPassword === '') {
      alert('Please confirm your password');
      return;
    }

    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match');
      return;
    }

    const utilisateur: utilisateur = {
      nom: this.username,
      email: this.email,
      motDePasse: this.password,
    }

    this.auth.signUp(utilisateur).subscribe(response => {
      this.toastService.showToast('Vous êtes inscrit et connecté', 'success')
    }, error => {
      console.error('Erreur lors de l\'inscription de l\'utilisateur', error);
      this.toastService.showToast('Erreur lors de l\'inscription', 'error')
    });

    this.email = '';
    this.password = '';
    this.confirmPassword = '';
    this.username = '';
  }
}
