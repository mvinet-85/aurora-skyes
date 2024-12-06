import {Routes} from '@angular/router';
import {LoginComponent} from './pages/authentification/login/login.component';
import {RegisterComponent} from './pages/authentification/register/register.component';
import {VerifyEmailComponent} from './pages/authentification/verify-email/verify-email.component';
import {ForgotPasswordComponent} from './pages/authentification/forgot-password/forgot-password.component';
import {HomeComponent} from './pages/home/home.component';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify-email', component: VerifyEmailComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'home', component: HomeComponent},
  {path: '**', redirectTo: 'login'}
];
