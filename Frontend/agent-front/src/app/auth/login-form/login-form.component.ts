import { TokenService } from './../service/token.service';
import { AuthHttpService } from './../service/auth-http.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  loginForm: FormGroup;
  message = '';
  loading = false;

  constructor(private formBuilder: FormBuilder, private authHttpService: AuthHttpService,
              private tokenService: TokenService, public router: Router) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  onLogin() {
    const loginInfo = this.loginForm.value;
    this.message = '';
    this.loading = true;
    this.authHttpService.login(loginInfo).subscribe(
      (data) => {
        this.tokenService.saveToken(data.active_token);
        this.loading = false;
        this.router.navigate(['hotel']);
      },
      (error) => {
        console.log(error.error)
        this.loading = false;
        this.message = error.error;
      }
    );
  }

}
