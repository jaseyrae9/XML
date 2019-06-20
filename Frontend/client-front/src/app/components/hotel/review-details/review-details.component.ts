import { Component, OnInit, Input } from '@angular/core';
import { ReservationService } from 'src/app/services/hotel/reservation.service';
import { DatePipe } from '@angular/common';
import { Recension } from 'src/app/model/reservation/recension';

@Component({
  selector: 'app-review-details',
  templateUrl: './review-details.component.html',
  styleUrls: ['./review-details.component.css']
})
export class ReviewDetailsComponent implements OnInit {
  @Input() roomId;
  recensions: Recension[] = [];

  constructor(private reservationService: ReservationService,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.loadRecensions();
  }

  loadRecensions() {
    this.reservationService.getRecensions(this.roomId).subscribe(
      (data) => {
        this.recensions = data;
        console.log(data);
      }
    );
  }

}
