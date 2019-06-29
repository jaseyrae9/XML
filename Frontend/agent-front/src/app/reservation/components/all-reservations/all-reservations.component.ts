import { ReservationService } from './../../service/reservation.service';
import { AccommodationHTTPService } from './../../../accommodation/service/accommodation-http.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-all-reservations',
  templateUrl: './all-reservations.component.html',
  styleUrls: ['./all-reservations.component.css']
})
export class AllReservationsComponent implements OnInit {
  reservations: Reservation[] = [];

  constructor(private route: ActivatedRoute, private reservationService: ReservationService) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.reservationService.getReservations(id).subscribe(
      (data) => {
        this.reservations = data.results;
      }
    );
  }

}
