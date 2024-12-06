import {Component, inject, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../core/services/authentification/auth.service';
import {utilisateur} from '../../../core/models/utilisateur';


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
      console.log('Réponse du serveur:', response);
    }, error => {
      console.error('Erreur:', error);
    });

    this.email = '';
    this.password = '';
    this.confirmPassword = '';
    this.username = '';
  }
}
