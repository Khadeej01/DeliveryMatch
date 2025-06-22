import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

export const authGuard = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

export const roleGuard = (allowedRoles: string[]) => {
  return () => {
    const authService = inject(AuthService);
    const router = inject(Router);

    if (authService.isAuthenticated()) {
      if (authService.hasAnyRole(allowedRoles)) {
        return true;
      } else {
        router.navigate(['/dashboard']);
        return false;
      }
    } else {
      router.navigate(['/login']);
      return false;
    }
  };
};
