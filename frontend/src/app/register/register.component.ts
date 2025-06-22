import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService, RegisterRequest } from '../service/auth.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  isLoading = false;
  errorMessage = '';
  showPassword = false;
  showConfirmPassword = false;
  selectedRole = 'EXPEDITEUR';

  roles = [
    { value: 'EXPEDITEUR', label: 'Expéditeur', description: 'Envoyez vos colis' },
    { value: 'CONDUCTEUR', label: 'Conducteur', description: 'Transportez des colis' },
    { value: 'ADMIN', label: 'Administrateur', description: 'Gérez la plateforme' }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
      nom: ['', [Validators.required, Validators.minLength(2)]],
      prenom: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', [Validators.pattern(/^[0-9+\-\s()]+$/)]],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d@$!%*?&]{8,}$/)
      ]],
      confirmPassword: ['', [Validators.required]],
      role: ['EXPEDITEUR', [Validators.required]],
      acceptTerms: [true, [Validators.requiredTrue]]
    }, { validators: this.passwordMatchValidator });
  }

  ngOnInit(): void {
    // Redirect if already authenticated
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/dashboard']);
    }

    // Watch for role changes
    this.registerForm.get('role')?.valueChanges.subscribe(role => {
      this.selectedRole = role;
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';

      const registerData: RegisterRequest = {
        nom: this.registerForm.get('nom')?.value,
        prenom: this.registerForm.get('prenom')?.value,
        email: this.registerForm.get('email')?.value,
        telephone: this.registerForm.get('telephone')?.value,
        password: this.registerForm.get('password')?.value,
        role: this.registerForm.get('role')?.value
      };

      this.authService.register(registerData).subscribe({
        next: (response) => {
          if (response.success) {
            // Auto-login after successful registration
            this.authService.login({
              email: registerData.email,
              password: registerData.password
            }).subscribe({
              next: (loginResponse) => {
                if (loginResponse.success) {
                  this.router.navigate(['/dashboard']);
                }
              },
              error: (error) => {
                // Registration successful but login failed
                this.router.navigate(['/login']);
              }
            });
          } else {
            this.errorMessage = response.message || 'Erreur lors de l\'inscription';
          }
        },
        error: (error) => {
          this.errorMessage = error.message || 'Erreur lors de l\'inscription';
          this.isLoading = false;
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    } else {
      this.markFormGroupTouched();
    }
  }

  togglePasswordVisibility(field: 'password' | 'confirmPassword'): void {
    if (field === 'password') {
      this.showPassword = !this.showPassword;
    } else {
      this.showConfirmPassword = !this.showConfirmPassword;
    }
  }

  selectRole(role: string): void {
    this.registerForm.patchValue({ role });
  }

  getRoleIcon(role: string): string {
    switch (role) {
      case 'EXPEDITEUR':
        return 'fas fa-box';
      case 'CONDUCTEUR':
        return 'fas fa-truck';
      case 'ADMIN':
        return 'fas fa-user-shield';
      default:
        return 'fas fa-user';
    }
  }

  private passwordMatchValidator(form: FormGroup): { [key: string]: any } | null {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');

    if (password && confirmPassword && password.value !== confirmPassword.value) {
      return { passwordMismatch: true };
    }

    return null;
  }

  private markFormGroupTouched(): void {
    Object.keys(this.registerForm.controls).forEach(key => {
      const control = this.registerForm.get(key);
      control?.markAsTouched();
    });
  }

  getErrorMessage(controlName: string): string {
    const control = this.registerForm.get(controlName);

    if (control?.hasError('required')) {
      return 'Ce champ est requis';
    }

    if (controlName === 'email' && control?.hasError('email')) {
      return 'Email invalide';
    }

    if (controlName === 'nom' && control?.hasError('minlength')) {
      return 'Le nom doit contenir au moins 2 caractères';
    }

    if (controlName === 'prenom' && control?.hasError('minlength')) {
      return 'Le prénom doit contenir au moins 2 caractères';
    }

    if (controlName === 'password' && control?.hasError('minlength')) {
      return 'Le mot de passe doit contenir au moins 8 caractères';
    }

    if (controlName === 'password' && control?.hasError('pattern')) {
      return 'Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre';
    }

    if (controlName === 'telephone' && control?.hasError('pattern')) {
      return 'Numéro de téléphone invalide';
    }

    if (controlName === 'confirmPassword' && this.registerForm.hasError('passwordMismatch')) {
      return 'Les mots de passe ne correspondent pas';
    }

    return '';
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.registerForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  getPasswordStrength(): { strength: string; color: string; percentage: number } {
    const password = this.registerForm.get('password')?.value || '';

    if (!password) {
      return { strength: '', color: '', percentage: 0 };
    }

    let score = 0;
    if (password.length >= 8) score += 25;
    if (/[a-z]/.test(password)) score += 25;
    if (/[A-Z]/.test(password)) score += 25;
    if (/\d/.test(password)) score += 25;

    if (score <= 25) {
      return { strength: 'Faible', color: '#dc3545', percentage: score };
    } else if (score <= 50) {
      return { strength: 'Moyen', color: '#ffc107', percentage: score };
    } else if (score <= 75) {
      return { strength: 'Bon', color: '#17a2b8', percentage: score };
    } else {
      return { strength: 'Fort', color: '#28a745', percentage: score };
    }
  }
}
