import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

export interface User {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  telephone?: string;
  role: string;
  dateCreation?: string;
  derniereConnexion?: string;
  actif?: boolean;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  nom: string;
  prenom: string;
  email: string;
  password: string;
  telephone?: string;
  role: string;
}

export interface AuthResponse {
  success: boolean;
  message: string;
  accessToken?: string;
  refreshToken?: string;
  tokenType?: string;
  expiresIn?: number;
  user?: User;
}

export interface TokenValidationResponse {
  success: boolean;
  valid: boolean;
  message?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = 'http://localhost:8080/api/auth';
  private readonly TOKEN_KEY = 'access_token';
  private readonly REFRESH_TOKEN_KEY = 'refresh_token';
  private readonly USER_KEY = 'current_user';

  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.loadStoredAuth();
  }

  // Register new user
  register(registerData: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/register`, registerData)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Login user
  login(loginData: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, loginData)
      .pipe(
        tap(response => {
          if (response.success && response.accessToken) {
            this.setAuthData(response);
          }
        }),
        catchError(this.handleError)
      );
  }

  // Logout user
  logout(): Observable<AuthResponse> {
    const token = this.getToken();
    if (token) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

      return this.http.post<AuthResponse>(`${this.API_URL}/logout`, {}, { headers })
        .pipe(
          tap(() => this.clearAuthData()),
          catchError(error => {
            this.clearAuthData();
            return throwError(() => error);
          })
        );
    } else {
      this.clearAuthData();
      return new Observable(observer => {
        observer.next({ success: true, message: 'Déconnexion réussie' });
        observer.complete();
      });
    }
  }

  // Refresh token
  refreshToken(): Observable<AuthResponse> {
    const refreshToken = this.getRefreshToken();
    if (!refreshToken) {
      return throwError(() => new Error('Aucun token de rafraîchissement disponible'));
    }

    return this.http.post<AuthResponse>(`${this.API_URL}/refresh`, { refreshToken })
      .pipe(
        tap(response => {
          if (response.success && response.accessToken) {
            this.setAuthData(response);
          }
        }),
        catchError(error => {
          this.clearAuthData();
          return throwError(() => error);
        })
      );
  }

  // Get current user
  getCurrentUser(): Observable<AuthResponse> {
    const token = this.getToken();
    if (!token) {
      return throwError(() => new Error('Aucun token disponible'));
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.get<AuthResponse>(`${this.API_URL}/me`, { headers })
      .pipe(
        tap(response => {
          if (response.success && response.user) {
            this.currentUserSubject.next(response.user);
          }
        }),
        catchError(this.handleError)
      );
  }

  // Validate token
  validateToken(token: string): Observable<TokenValidationResponse> {
    return this.http.post<TokenValidationResponse>(`${this.API_URL}/validate`, { token })
      .pipe(
        catchError(this.handleError)
      );
  }

  // Health check
  healthCheck(): Observable<any> {
    return this.http.get(`${this.API_URL}/public/health`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Token management
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getRefreshToken(): string | null {
    return localStorage.getItem(this.REFRESH_TOKEN_KEY);
  }

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  setRefreshToken(token: string): void {
    localStorage.setItem(this.REFRESH_TOKEN_KEY, token);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  removeRefreshToken(): void {
    localStorage.removeItem(this.REFRESH_TOKEN_KEY);
  }

  // User management
  getCurrentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return this.isAuthenticatedSubject.value;
  }

  hasRole(role: string): boolean {
    const user = this.getCurrentUserValue();
    return user ? user.role.toUpperCase() === role.toUpperCase() : false;
  }

  hasAnyRole(roles: string[]): boolean {
    const user = this.getCurrentUserValue();
    if (!user) return false;

    return roles.some(role =>
      user.role.toUpperCase() === role.toUpperCase()
    );
  }

  // Navigation helpers
  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }

  navigateToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }

  navigateToHome(): void {
    this.router.navigate(['/']);
  }

  // Private methods
  private setAuthData(response: AuthResponse): void {
    if (response.accessToken) {
      this.setToken(response.accessToken);
    }
    if (response.refreshToken) {
      this.setRefreshToken(response.refreshToken);
    }
    if (response.user) {
      this.currentUserSubject.next(response.user);
      localStorage.setItem(this.USER_KEY, JSON.stringify(response.user));
    }
    this.isAuthenticatedSubject.next(true);
  }

  private clearAuthData(): void {
    this.removeToken();
    this.removeRefreshToken();
    localStorage.removeItem(this.USER_KEY);
    this.currentUserSubject.next(null);
    this.isAuthenticatedSubject.next(false);
  }

  private loadStoredAuth(): void {
    const token = this.getToken();
    const userStr = localStorage.getItem(this.USER_KEY);

    if (token && userStr) {
      try {
        const user = JSON.parse(userStr);
        this.currentUserSubject.next(user);
        this.isAuthenticatedSubject.next(true);
      } catch (error) {
        console.error('Error parsing stored user data:', error);
        this.clearAuthData();
      }
    }
  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'Une erreur est survenue';

    if (error.error?.message) {
      errorMessage = error.error.message;
    } else if (error.message) {
      errorMessage = error.message;
    } else if (error.status === 401) {
      errorMessage = 'Non autorisé';
    } else if (error.status === 403) {
      errorMessage = 'Accès interdit';
    } else if (error.status === 404) {
      errorMessage = 'Ressource non trouvée';
    } else if (error.status === 500) {
      errorMessage = 'Erreur serveur';
    }

    console.error('AuthService error:', error);
    return throwError(() => new Error(errorMessage));
  }
} 
