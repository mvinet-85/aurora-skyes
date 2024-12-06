import {HttpClient} from '@angular/common/http';
import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {utilisateur} from '../../../core/models/user';
import {AuthService} from '../../../core/services/authentification/auth.service';
import {utilisateurLogin} from '../../../core/models/userSignIn';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [
    FormsModule,
    ReactiveFormsModule,
  ],
  standalone: true
})
export class LoginComponent {

  public utilisateur: utilisateur = {
    nom: '',
    email: '',
    motDePasse: '',
  };

  public utilisateurLoged: utilisateurLogin = {
    email: '',
    motDePasse: '',
  };

  public loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required]),
    mdp: new FormControl('', [Validators.required])
  });

  private authService: AuthService = inject(AuthService);

  constructor(private http: HttpClient) {
  }

  onSubmit() {
    this.utilisateurLoged.email = this.loginForm.controls['email'].value;
    this.utilisateurLoged.motDePasse = this.loginForm.controls['mdp'].value;
    this.authService.signIn(this.utilisateurLoged).subscribe(
      (data) => {
        this.utilisateur = data;
        console.log(this.utilisateur);
      },
      (error) => {
        console.error('Erreur lors de la récupération de l\'utilisateur', error);
      }
    );
  }
}
