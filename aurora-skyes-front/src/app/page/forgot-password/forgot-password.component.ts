import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

class AuthService {
}

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss',
})
export class ForgotPasswordComponent implements OnInit {
  email: string = '';
  errorMessage: any;
  successMessage: any;
  private auth: any;

  ngOnInit(): void {
  }

  forgot_password(email: string) {
    this.auth.forgot_password(this.email);
    this.email = '';
  }

  resetPassword() {

  }
}
