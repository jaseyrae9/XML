import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RoutingModule } from './app-routing.module';

import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
// datepicker
import { BsDatepickerModule} from 'ngx-bootstrap';
import {RatingModule} from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';

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
import { MapComponent } from './components/map/map.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { EditProfileFormComponent } from './components/user/edit-profile-form/edit-profile-form.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { AllReservationsComponent } from './components/reservations-history/all-reservations/all-reservations.component';
import { ReservationComponent } from './components/reservations-history/reservation/reservation.component';
import { ReservationDetailsComponent } from './components/reservations-history/reservation-details/reservation-details.component';
import { HotelPageComponent } from './components/hotel/hotel-page/hotel-page.component';
import { RoomBasicInfoComponent } from './components/hotel/room-basic-info/room-basic-info.component';
import { RoomPageComponent } from './components/hotel/room-page/room-page.component';
import { NgxGalleryModule } from 'ngx-gallery';
import { FullCalendarModule } from '@fullcalendar/angular';
import { AddressDetailsComponent } from './components/address-details/address-details.component';
import { GalleryComponent } from './components/hotel/gallery/gallery.component';
import { PriceTableComponent } from './components/hotel/price-table/price-table.component';
import { ReviewDetailsComponent } from './components/hotel/review-details/review-details.component';
import { NgSelectModule } from '@ng-select/ng-select';

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
    AllHotelsPageComponent,
    MapComponent,
    ProfileComponent,
    EditProfileFormComponent,
    SearchPageComponent,
    AllReservationsComponent,
    ReservationComponent,
    ReservationDetailsComponent,
    HotelPageComponent,
    RoomBasicInfoComponent,
    RoomPageComponent,
    AddressDetailsComponent,
    GalleryComponent,
    PriceTableComponent,
    ReviewDetailsComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ModalModule.forRoot(),
    ReactiveFormsModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule,
    BsDatepickerModule.forRoot(),
    NgxGalleryModule,
    RatingModule,
    FullCalendarModule, // for FullCalendar!
    NgSelectModule
  ],
  providers: [RoleGuardService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
