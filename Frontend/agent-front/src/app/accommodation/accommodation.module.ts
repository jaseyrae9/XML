import { ReservationModule } from './../reservation/reservation.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { NgxGalleryModule } from 'ngx-gallery';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { RatingModule } from 'ngx-bootstrap/rating';
import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
import { FullCalendarModule } from '@fullcalendar/angular';
import { NgSelectModule } from '@ng-select/ng-select';

import { LocationModule } from './../location/location.module';
import { HotelDisplayComponent } from './components/hotel-display/hotel-display.component';
import { RoomPreviewComponent } from './components/room-preview/room-preview.component';
import { RoomDisplayComponent } from './components/room-display/room-display.component';
import { GaleryComponent } from './components/galery/galery.component';
import { PriceTableComponent } from './components/price-table/price-table.component';
import { ReviewDisplayComponent } from './components/review-display/review-display.component';
import { RoomFormComponent } from './components/room-form/room-form.component';
import { UploadImageFormComponent } from './components/upload-image-form/upload-image-form.component';

@NgModule({
  declarations: [
    HotelDisplayComponent,
    RoomPreviewComponent,
    RoomDisplayComponent,
    GaleryComponent,
    PriceTableComponent,
    ReviewDisplayComponent,
    RoomFormComponent,
    UploadImageFormComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    NgxGalleryModule,
    LocationModule,
    ReservationModule,
    BsDatepickerModule.forRoot(),
    RatingModule.forRoot(),
    FullCalendarModule,
    NgSelectModule,
    ReactiveFormsModule,
    FormsModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
  ],
  exports: [
    HotelDisplayComponent, RoomDisplayComponent, RoomFormComponent, RoomPreviewComponent
  ],
  providers: [
    DatePipe
  ]
})
export class AccommodationModule { }
