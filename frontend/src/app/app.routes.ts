import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent} from './shared/components/about/about.component';
import { DashboardComponent} from './shared/components/dashboard/dashboard.component';
import { ContactComponent} from './pages/contact/contact.component';

import { ServicesComponent} from './pages/services/services.component';
import { RegisterComponent} from './register/register.component';
import { LoginComponent } from './login/login.component';




export const routes: Routes = [
  // Site principal
  { path: '', component: HomeComponent, title: 'Accueil - DeliveryMatch' },
  { path: 'about', component: AboutComponent, title: 'À Propos - DeliveryMatch' },
  { path: 'services', component: ServicesComponent, title: 'Services - DeliveryMatch' },
  { path: 'contact', component: ContactComponent, title: 'Contact - DeliveryMatch' },
  { path: 'login', component: LoginComponent, title: 'Connexion - DeliveryMatch' },
  { path: 'register', component: RegisterComponent, title: 'Inscription - DeliveryMatch' },

  // Tableau de bord
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      // Rediriger /dashboard vers /dashboard/overview ou une autre page par défaut
      // { path: '', component: DashboardOverviewComponent, title: 'Aperçu - Dashboard' }
    ]
  },

  // Redirection pour les chemins non trouvés
  { path: '**', redirectTo: '', pathMatch: 'full' }
];


