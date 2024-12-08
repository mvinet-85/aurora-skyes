import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../core/services/authentification/auth.service';
import {utilisateur} from '../../../core/models/utilisateur';
import {ToastService} from '../../../core/services/toast/toast.service';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule,],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    mdp: new FormControl('', [Validators.required, Validators.minLength(6)]),
    mdpConfirmation: new FormControl('', [Validators.required, Validators.minLength(6)]),
    nom: new FormControl('', [Validators.required, Validators.minLength(2)])
  });

  private readonly auth: AuthService = inject(AuthService);
  private readonly toastService: ToastService = inject(ToastService);

  ngOnInit(): void {
  }

  register() {
    if (this.registerForm.controls['nom'].value === '') {
      alert('Le nom est obligatoire');
      return;
    }
    if (this.registerForm.controls['email'].value === '') {
      alert('L\'email est obligatoire');
      return;
    }
    if (this.registerForm.controls['mdp'].value === '') {
      alert('Le mot de passe est obligatoire');
      return;
    }
    if (this.registerForm.controls['mdpConfirmation'].value === '') {
      alert('La confirmation de mot de passe est obligatoire');
      return;
    }

    if (this.registerForm.controls['mdp'].value !== this.registerForm.controls['mdpConfirmation'].value) {
      alert('Les deux mots de passe sont différents');
      return;
    }

    const utilisateur: utilisateur = {
      nom: this.registerForm.controls['nom'].value,
      email: this.registerForm.controls['email'].value,
      motDePasse: this.registerForm.controls['mdpConfirmation'].value
    }

    this.auth.signUp(utilisateur).subscribe(response => {
      this.toastService.showToast('Vous êtes inscrit et connecté', 'success')
    }, error => {
      console.error('Erreur lors de l\'inscription de l\'utilisateur', error);
      this.toastService.showToast('Erreur lors de l\'inscription', 'error')
    });

    this.registerForm.reset();
  }
}
