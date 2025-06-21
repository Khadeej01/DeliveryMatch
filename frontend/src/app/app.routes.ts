import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  // { path: 'login', loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent) },
  // { path: 'register', loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent) },
  // { path: 'trajets', loadComponent: () => import('./pages/trajets/trajets.component').then(m => m.TrajetsComponent) },
  // { path: 'demandes', loadComponent: () => import('./pages/demandes/demandes.component').then(m => m.DemandesComponent) },
  // { path: 'dashboard', loadComponent: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent) },
  { path: '**', redirectTo: '' }
];
