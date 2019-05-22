import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RoutingModule } from './app-routing.module';

import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';

import { AppComponent } from './app.component';
import { NavigationComponent } from './components/basic-components/navigation/navigation.component';
import { LoginFormComponent } from './components/user/login-form/login-form.component';
import { RegisterFormComponent } from './components/user/register-form/register-form.component';
import { ChangePasswordFormComponent } from './components/user/change-password-form/change-password-form.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { BannerComponent } from './components/basic-components/banner/banner.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { RoleGuardService } from './auth/role-guard.service';
import { HotelBasicDetailsComponent } from './components/hotel/hotel-basic-details/hotel-basic-details.component';
import { AllHotelsPageComponent } from './pages/all-hotels-page/all-hotels-page.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ChangePasswordFormComponent,
    HomePageComponent,
    ErrorPageComponent,
    BannerComponent,
    HotelBasicDetailsComponent,
    AllHotelsPageComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ModalModule.forRoot(),
    ReactiveFormsModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
  ],
  providers: [RoleGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
