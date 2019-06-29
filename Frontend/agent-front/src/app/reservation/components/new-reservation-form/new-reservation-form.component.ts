import { DatePipe } from '@angular/common';
import { ReservationService } from './../../service/reservation.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker/public_api';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-new-reservation-form',
  templateUrl: './new-reservation-form.component.html',
  styleUrls: ['./new-reservation-form.component.css']
})
export class NewReservationFormComponent implements OnInit {
  @Input() roomId: number;

  datePickerConfig: Partial<BsDatepickerConfig> = Object.assign({},
    {
        containerClass: 'theme-default',
        dateInputFormat: 'DD-MM-YYYY'
  });

  newReservationForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private reservationService: ReservationService, private datePipe: DatePipe) {
    this.newReservationForm = this.formBuilder.group({
      date: ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  submitReservation() {
    const reservationInfo = {
      roomId : this.roomId,
      dateFrom: this.datePipe.transform(this.newReservationForm.value.date[0], 'yyyy-MM-dd'),
      dateTo: this.datePipe.transform(this.newReservationForm.value.date[1], 'yyyy-MM-dd')
    };
    this.reservationService.addReservation(reservationInfo).subscribe(
      (data) => {
        alert('Reservation created');
      },
      (error) => {
        alert('Reservation error');
      }
    )
    this.newReservationForm.reset();
  }

}
