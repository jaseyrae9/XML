import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Recension } from 'src/app/model/reservation/recension';
import { ReservationService } from 'src/app/services/hotel/reservation.service';

@Component({
  selector: 'app-create-recension',
  templateUrl: './create-recension.component.html',
  styleUrls: ['./create-recension.component.css', '../../../shared/css/inputField.css']
})
export class CreateRecensionComponent implements OnInit {
  @Output() recensionCreated: EventEmitter<Recension> = new EventEmitter();
  @Input() reservationId;
  postReviewForm: FormGroup;
  recensionRequest: Recension = new Recension();
  recensionResponse: Recension = new Recension();

  errorMessage = '';

  constructor(private formBuilder: FormBuilder,
              private reservationService: ReservationService) {
    this.postReviewForm = this.formBuilder.group({
      rating: ['', [Validators.required]],
      title: ['', [Validators.required]],
      comment: ['', [Validators.required]],
    });
   }

  ngOnInit() {
  }

  postReview() {
    const value = this.postReviewForm.value;
    this.recensionRequest.rating = value.rating;
    this.recensionRequest.title = value.title;
    this.recensionRequest.comment = value.comment;
    this.recensionRequest.reservationId = this.reservationId;
    this.reservationService.postRecension(this.recensionRequest).subscribe(
      (data) => {
        console.log(data);
        this.recensionResponse = data;
        this.recensionCreated.emit(data);
      },
      (error) => {
        this.errorMessage = error.error.message;
      }

    );
  }

}
