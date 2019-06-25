import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from 'src/app/services/hotel/reservation.service';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reservation-page',
  templateUrl: './reservation-page.component.html',
  styleUrls: ['./reservation-page.component.css', '../../../shared/css/inputField.css']
})
export class ReservationPageComponent implements OnInit {

  reservationId;
  @Input() reservation: ReservationPreview = new ReservationPreview();
  createdOn;

  get reservationStatus() { return ReservationStatus; }

  constructor(public datePipe: DatePipe,
              private route: ActivatedRoute,
              private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationId = this.route.snapshot.paramMap.get('id');
    this.loadReservation();
  }

  loadReservation() {
    this.reservationService.getReservation(this.reservationId).subscribe(
      (data) => {
        this.reservation = data;
        this.createdOn = this.datePipe.transform(this.reservation.created, 'yyyy-MM-dd');
        console.log('Rezervacija', data);
      }
    );
  }
}
