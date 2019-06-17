import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
import { ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewReservationFormComponent } from './components/new-reservation-form/new-reservation-form.component';

@NgModule({
  declarations: [NewReservationFormComponent],
  imports: [
    CommonModule,
    BsDatepickerModule.forRoot(),
    ReactiveFormsModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
  ],
  exports: [
    NewReservationFormComponent
  ]
})
export class ReservationModule { }
