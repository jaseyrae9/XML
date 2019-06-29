import { HttpClientModule } from '@angular/common/http';
import { AccommodationModule } from './../accommodation/accommodation.module';
import { RouterModule } from '@angular/router';
import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
import { ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { NewReservationFormComponent } from './components/new-reservation-form/new-reservation-form.component';
import { AllReservationsComponent } from './components/all-reservations/all-reservations.component';
import { ReservationPreviewComponent } from './components/reservation-preview/reservation-preview.component';
import { ReservationDetailsComponent } from './components/reservation-details/reservation-details.component';
import { MessageComponent } from './components/message/message.component';

@NgModule({
  declarations: [NewReservationFormComponent, AllReservationsComponent, ReservationPreviewComponent, ReservationDetailsComponent, MessageComponent],
  imports: [
    CommonModule,
    BsDatepickerModule.forRoot(),
    ReactiveFormsModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule,
    RouterModule,
    HttpClientModule
  ],
  exports: [
    NewReservationFormComponent, AllReservationsComponent, ReservationDetailsComponent
  ],
  providers: [
    DatePipe
  ]
})
export class ReservationModule { }
