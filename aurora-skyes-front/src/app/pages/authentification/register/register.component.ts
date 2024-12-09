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
    const utilisateur: utilisateur = {
      nom: this.registerForm.controls['nom'].value,
      email: this.registerForm.controls['email'].value,
      motDePasse: this.registerForm.controls['mdpConfirmation'].value
    }

    this.auth.signUp(utilisateur).subscribe(response => {
      this.toastService.showToast('Vous êtes inscrit et connecté', 'success')
    }, error => {
      if (error.status === 403) {
        this.toastService.showToast('Cet email appartient déjà à un compte', 'error')
      } else {
        this.toastService.showToast('Erreur lors de l\'inscription', 'error')
      }
      console.error('Erreur lors de l\'inscription de l\'utilisateur', error);
    });

    this.registerForm.reset();
  }
}
