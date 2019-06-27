import { TokenService } from './token.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {
  constructor(public tokenService: TokenService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (!this.tokenService.checkIsLoggedIn()){
      this.router.navigate(['']);
    }
    return this.tokenService.checkIsLoggedIn();
  }
}
