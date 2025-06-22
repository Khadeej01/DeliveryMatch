import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService, User } from '../../../service/auth.service';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  isAuthenticated = false;
  currentUser: User | null = null;
  isMenuOpen = false;
  isUserMenuOpen = false;

  private authSubscription: Subscription | null = null;
  private userSubscription: Subscription | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // Subscribe to authentication state
    this.authSubscription = this.authService.isAuthenticated$.subscribe(
      isAuth => {
        this.isAuthenticated = isAuth;
        if (!isAuth) {
          this.currentUser = null;
        }
      }
    );

    // Subscribe to current user
    this.userSubscription = this.authService.currentUser$.subscribe(
      user => {
        this.currentUser = user;
      }
    );

    // Load current user if authenticated
    if (this.authService.isAuthenticated()) {
      this.authService.getCurrentUser().subscribe();
    }
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }

  toggleMobileMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
    // Close user menu when mobile menu opens
    if (this.isMenuOpen) {
      this.isUserMenuOpen = false;
    }
  }

  toggleUserMenu(): void {
    this.isUserMenuOpen = !this.isUserMenuOpen;
    // Close mobile menu when user menu opens
    if (this.isUserMenuOpen) {
      this.isMenuOpen = false;
    }
  }

  closeMenus(): void {
    this.isMenuOpen = false;
    this.isUserMenuOpen = false;
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.closeMenus();
        // Navigation will be handled by the service
      },
      error: (error) => {
        console.error('Logout error:', error);
        this.closeMenus();
        // Force logout even if server call fails
        this.authService.navigateToHome();
      }
    });
  }

  hasRole(role: string): boolean {
    return this.authService.hasRole(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return this.authService.hasAnyRole(roles);
  }

  getUserDisplayName(): string {
    if (this.currentUser) {
      return `${this.currentUser.prenom} ${this.currentUser.nom}`;
    }
    return '';
  }

  getUserRoleDisplay(): string {
    if (this.currentUser) {
      switch (this.currentUser.role.toUpperCase()) {
        case 'EXPEDITEUR':
          return 'Expéditeur';
        case 'CONDUCTEUR':
          return 'Conducteur';
        case 'ADMIN':
          return 'Administrateur';
        default:
          return this.currentUser.role;
      }
    }
    return '';
  }

  // Handle clicks outside menus to close them
  onDocumentClick(event: Event): void {
    const target = event.target as HTMLElement;
    if (!target.closest('.user-menu') && !target.closest('.mobile-menu-toggle')) {
      this.closeMenus();
    }
  }
}
