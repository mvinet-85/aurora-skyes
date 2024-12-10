import {provideRouter, Routes} from '@angular/router';
import {LoginComponent} from './pages/authentification/login/login.component';
import {RegisterComponent} from './pages/authentification/register/register.component';
import {VerifyEmailComponent} from './pages/authentification/verify-email/verify-email.component';
import {ForgotPasswordComponent} from './pages/authentification/forgot-password/forgot-password.component';
import {HomeComponent} from './pages/home/home.component';
import {AuthGuard} from './core/guards/auth.guard';
<<<<<<< HEAD
import {ReservationComponent} from './pages/reservation/reservation.component';
import { ReservationStateComponent } from './pages/reservation-state/reservation-state.component';
=======
import {ReservationStateComponent} from './pages/reservation-state/reservation-state.component';
>>>>>>> ff0c03c (Ajout de la route)

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify-email', component: VerifyEmailComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard],
  },
  {
    path: 'reservation', component: ReservationComponent, canActivate: [AuthGuard],
  },
  {
    path: 'stat', component: ReservationStateComponent, canActivate: [AuthGuard],
  },
  {path: '**', redirectTo: 'login'}
];

export const appRoutingProviders = [provideRouter(routes)];
