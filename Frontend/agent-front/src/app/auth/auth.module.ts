import { RoleGuardService } from './service/route-guard.service';
import { JwtInterceptor } from './interceptor/jwt-interceptor';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

/*ZA FORME*/
import { ReactiveFormsModule} from '@angular/forms';
import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';

import { LoginFormComponent } from './login-form/login-form.component';


@NgModule({
  declarations: [LoginFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
  ],
  exports: [
    LoginFormComponent
  ],
  providers: [
    JwtInterceptor, RoleGuardService
  ]
})
export class AuthModule { }
