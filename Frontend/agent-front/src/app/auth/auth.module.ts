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
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
  ],
  exports: [
    LoginFormComponent
  ]
})
export class AuthModule { }
