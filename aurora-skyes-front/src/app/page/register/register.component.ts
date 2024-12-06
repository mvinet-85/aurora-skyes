import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';


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
  private auth: any;

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
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

    this.auth.register(
      this.username,
      this.email,
      this.password,
    );

    this.email = '';
    this.password = '';
    this.confirmPassword = '';
    this.username = '';
  }
}
