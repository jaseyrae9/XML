import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RoutingModule } from './app-routing.module';


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


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ChangePasswordFormComponent,
    HomePageComponent,
    ErrorPageComponent,
    BannerComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ModalModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
