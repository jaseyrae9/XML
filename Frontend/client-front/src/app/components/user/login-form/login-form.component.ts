import { JwtResponse } from './../../../auth/jwt-response';
import { Component, OnInit, ViewChild } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { AuthLoginInfo } from 'src/app/auth/login-info';
import { AuthService } from 'src/app/auth/auth.service';
import * as decode from 'jwt-decode';
import { TokenPayload } from 'src/app/model/token-payload';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['../../../shared/css/inputField.css']
})
export class LoginFormComponent implements OnInit {

  form: any = {};
  errorMessage = '';

  private loginInfo: AuthLoginInfo;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() {
  }

  onLogin() {

    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password
    );

    this.authService.attemptAuth(this.loginInfo).subscribe(
      (data: JwtResponse) => {
        this.tokenStorage.saveUsername(this.form.username);
          this.tokenStorage.saveToken(data.token);
          const tokenPayload: TokenPayload = decode(data.token);
          this.tokenStorage.saveRoles(tokenPayload.roles);
          // this.tokenStorage.saveComapny(tokenPayload.companyId);
          console.log(tokenPayload);
      },
      error => {
        this.errorMessage = error.error.details;
      }
    );

  }

  reloadPage() {
    window.location.reload();
  }

}
