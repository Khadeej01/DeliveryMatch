import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent} from './shared/components/about/about.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'Accueil - DeliveryMatch' },
  { path: 'about', component: AboutComponent, title: 'À Propos - DeliveryMatch' },

  // Redirection pour les chemins non trouvés
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
