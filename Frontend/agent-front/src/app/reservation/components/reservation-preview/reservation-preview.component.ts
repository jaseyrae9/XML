import { Component, OnInit, Input } from '@angular/core';
import { ReservationService } from '../../service/reservation.service';

@Component({
  selector: 'app-reservation-preview',
  templateUrl: './reservation-preview.component.html',
  styleUrls: ['./reservation-preview.component.css']
})
export class ReservationPreviewComponent implements OnInit {
  @Input() reservation: Reservation;
  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  approveReservation() {
    this.reservationService.approveReservation(this.reservation.id).subscribe(
      (data) => {
        this.reservation.status = 'HAPPENED';
      }
    );
  }

}
