import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';
import { ReservationService } from 'src/app/services/hotel/reservation.service';

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css', '../../../shared/css/inputField.css']
})
export class ReservationDetailsComponent implements OnInit {
  @Input() reservation: ReservationPreview = new ReservationPreview();
  @Output() reservationCanceled: EventEmitter<ReservationPreview> = new EventEmitter();

  errorMessage = '';

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  cancelReservation() {
    this.reservationService.cancelReservation(this.reservation.id).subscribe(
      (data) => {
        console.log('otkazujemo: ', data.id);
        alert('Otkazana rezervacija: ' + data.id);
        this.reservationCanceled.emit(data);
      },
      (error) => {
        console.log('ne mozee', error);
        this.errorMessage = error.error.message;
      }
    );

  }

}
