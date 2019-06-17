import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker/public_api';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-reservation-form',
  templateUrl: './new-reservation-form.component.html',
  styleUrls: ['./new-reservation-form.component.css']
})
export class NewReservationFormComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig> = Object.assign({},
    {
        containerClass: 'theme-default',
        dateInputFormat: 'DD-MM-YYYY'
  });

  newReservationForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.newReservationForm = this.formBuilder.group({
      date: ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  submitReservation() {
    alert('Adding new reservation');
    this.newReservationForm.reset();
  }

}
