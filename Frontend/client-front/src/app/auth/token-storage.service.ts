import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Role } from 'src/app/model/role';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'Username';
const ROLES_KEY = 'roles';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private isLoggedIn = new Subject<boolean>();
  private username = new Subject<string>();
  private roles = new Subject<Role[]>();

  public logggedInEmitter = this.isLoggedIn.asObservable();
  public usernameEmitter = this.username.asObservable();

  public isCustomer = false;
  public isVistor = false;

  constructor() {
    this.isLoggedIn.next(false);
    this.getUsername();
    this.getRoles();
  }

  loggedInEmitChange(loggedIn: boolean) {
    this.isLoggedIn.next(loggedIn);
  }

  usernameEmitChange(username: string) {
    this.username.next(username);
  }

  public saveToken(token: string) {
    window.sessionStorage.setItem(TOKEN_KEY, token);
    this.loggedInEmitChange(true);
  }

  public saveUsername(username: string) {
    window.sessionStorage.setItem(USERNAME_KEY, username);
    this.usernameEmitChange(username);
  }

  rolesEmitChange(roles: Role[]) {
    this.roles.next(roles);
  }

  public saveRoles(roles: Role[]) {
    window.sessionStorage.setItem(ROLES_KEY, JSON.stringify(roles));
    this.checkRoles();
    this.rolesEmitChange(roles);
  }

  public checkRoles() {
    this.isCustomer = false;
    this.isVistor  = false;
    const roles = this.getRoles();
    if (roles) {
      for (const role of roles) {
        if ( role.authority === 'ROLE_CUSTOMER') {
          this.isCustomer = true;
        }
      }
    } else {
      this.isVistor = true;
    }
  }

  public getRoles(): Role[] {
    if (sessionStorage.getItem(ROLES_KEY)) {
      return JSON.parse(sessionStorage.getItem(ROLES_KEY));
    }
    return null;
  }

  signOut() {
    window.sessionStorage.clear();
    this.loggedInEmitChange(false);
    this.usernameEmitChange(null);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public checkIsLoggedIn(): boolean {
    // TODO: Promeniti da proveri vazi li token i dalje
    if (sessionStorage.getItem(TOKEN_KEY)) {
      return true;
    } else {
      return false;
    }
  }

}




