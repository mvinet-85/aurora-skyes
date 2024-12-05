import {HttpClient} from '@angular/common/http';
import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [
    FormsModule,
  ],
  standalone: true
})
export class LoginComponent {
  utilisateur = {
    nom: '',
    email: '',
    motDePasse: '',
  };

  constructor(private http: HttpClient) {
  }

  onSubmit() {
    console.log('Formulaire soumis:', this.utilisateur);

    // Envoyer les données à l'API backend
    this.http
      .post('http://localhost:8080/api/utilisateur/login', this.utilisateur)
      .subscribe(
        (response) => {
          console.log('Connexion réussie :', response);
          // Rediriger ou afficher un message de succès
        },
        (error) => {
          console.error('Erreur lors de la connexion :', error);
          // Gérer l'erreur (ex. : afficher un message d'erreur)
        }
      );
  }
}
