import { AccommodationModule } from './accommodation/accommodation.module';
import { AuthModule } from './auth/auth.module';
import { BasicUiModule } from './basic-ui/basic-ui.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HotelPageComponent } from './pages/hotel-page/hotel-page.component';
import { RoomPageComponent } from './pages/room-page/room-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HotelPageComponent,
    RoomPageComponent
  ],
  imports: [
    BrowserModule, BasicUiModule, AuthModule, RoutingModule, AccommodationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
