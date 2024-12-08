import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {utilisateur} from '../../../core/models/utilisateur';
import {AuthService} from '../../../core/services/authentification/auth.service';
import {ToastService} from '../../../core/services/toast/toast.service';

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

  public utilisateurLoged: utilisateur = {
    email: '',
    motDePasse: '',
  };

  public loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required]),
    mdp: new FormControl('', [Validators.required])
  });

  private authService: AuthService = inject(AuthService);
  private readonly toastService: ToastService = inject(ToastService);

  constructor() {
  }

  onSubmit() {
    this.utilisateurLoged.email = this.loginForm.controls['email'].value;
    this.utilisateurLoged.motDePasse = this.loginForm.controls['mdp'].value;
    this.authService.signIn(this.utilisateurLoged).subscribe(
      (data) => {
        this.utilisateur = data;
        this.toastService.showToast('Vous êtes connecté', 'info')
      },
      (error) => {
        console.error('Erreur lors de la connexion de l\'utilisateur', error);
        this.toastService.showToast('Erreur lors de la connexion de l\'utilisateur', 'error')
      }
    );
  }
}
