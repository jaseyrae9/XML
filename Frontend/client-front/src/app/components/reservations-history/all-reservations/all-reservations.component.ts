import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/services/hotel/reservation.service';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';

@Component({
  selector: 'app-all-reservations',
  templateUrl: './all-reservations.component.html',
  styleUrls: ['./all-reservations.component.css', '../../../shared/css/inputField.css']
})
export class AllReservationsComponent implements OnInit {

  reservations: ReservationPreview[] = [];

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.loadReservations();
  }

  loadReservations() {
    this.reservationService.getReservations().subscribe(
      (data) => {
        this.reservations = data.content;
        console.log('Rezervacije: ', this.reservations);
      }
    );
  }

  reservationCanceled(reservation: ReservationPreview) {
    const i = this.reservations.findIndex(e => e.id === reservation.id);
    if (i !== -1) {
      this.reservations.splice(i, 1, reservation);
      console.log('Res is splice!');
    }
  }

}
