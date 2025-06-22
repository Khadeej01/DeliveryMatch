import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  isSidebarOpen = true;

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  // Placeholder user data
  user = {
    name: 'John Doe',
    email: 'john.doe@example.com',
    avatar: 'https://i.pravatar.cc/150?img=5'
  };

  navLinks = [
    { path: '/dashboard', icon: 'home', label: 'Aperçu' },
    { path: '/dashboard/trips', icon: 'route', label: 'Mes Trajets' },
    { path: '/dashboard/packages', icon: 'package', label: 'Mes Colis' },
    { path: '/dashboard/profile', icon: 'user', label: 'Mon Profil' },
    { path: '/dashboard/settings', icon: 'settings', label: 'Paramètres' }
  ];
}
