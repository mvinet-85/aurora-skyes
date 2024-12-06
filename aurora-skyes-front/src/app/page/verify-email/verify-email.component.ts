import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-verify-email',
  standalone: true,
  imports: [],
  templateUrl: './verify-email.component.html',
  styleUrl: './verify-email.component.scss',
})
export class VerifyEmailComponent {
  constructor(private router: Router) {
  }

  goBack() {
    this.router.navigate(['/login']);
  }

  resendEmail() {
    console.log('Email de vérification renvoyé');
    
  }
}
