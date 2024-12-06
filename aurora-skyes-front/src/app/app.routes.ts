import {Routes} from '@angular/router';
import {LoginComponent} from './page/login/login.component';
import {RegisterComponent} from './page/register/register.component';
import {VerifyEmailComponent} from './page/verify-email/verify-email.component';
import {ForgotPasswordComponent} from './page/forgot-password/forgot-password.component';
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
