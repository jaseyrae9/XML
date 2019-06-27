import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(public router: Router) {
  }

  public saveToken(token: string) {
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  signOut() {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }

  public checkIsLoggedIn(): boolean {
    if (sessionStorage.getItem(TOKEN_KEY)) {
      return true;
    } else {
      return false;
    }
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
}
