import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './auth/auth-interceptor';
import { ErrorInterceptor } from './auth/response-interceptor';
import { AgmCoreModule } from '@agm/core';

import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
// datepicker
import { BsDatepickerModule} from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';

import { AppComponent } from './app.component';
import { NavigationComponent } from './components/basic-components/navigation/navigation.component';
import { LoginFormComponent } from './components/user/login-form/login-form.component';
import { RegisterFormComponent } from './components/user/register-form/register-form.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { BannerComponent } from './components/basic-components/banner/banner.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { RoleGuardService } from './auth/role-guard.service';
import { HotelBasicDetailsComponent } from './components/hotel/hotel-basic-details/hotel-basic-details.component';
import { AllHotelsPageComponent } from './pages/all-hotels-page/all-hotels-page.component';
import { MapComponent } from './components/map/map.component';
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
import {RatingModule} from 'ngx-rating';
import { ReservationPageComponent } from './components/reservations-history/reservation-page/reservation-page.component';
import { SearchedRoomBasicInfoComponent } from './components/hotel/searched-room-basic-info/searched-room-basic-info.component';
import {NgPipesModule} from 'ngx-pipes';
import { CreateRecensionComponent } from './components/reservations-history/create-recension/create-recension.component';
import { MessagesDetailsComponent } from './components/reservations-history/messages-details/messages-details.component';
import { FooterComponent } from './components/basic-components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginFormComponent,
    RegisterFormComponent,
    HomePageComponent,
    ErrorPageComponent,
    BannerComponent,
    HotelBasicDetailsComponent,
    AllHotelsPageComponent,
    MapComponent,
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
    ReservationPageComponent,
    SearchedRoomBasicInfoComponent,
    CreateRecensionComponent,
    MessagesDetailsComponent,
    FooterComponent
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
    NgSelectModule,
    NgPipesModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAha_PuYkkB226RBgsn81j3CP7vG-Mv1ig'
    })
  ],
  providers: [RoleGuardService, DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
