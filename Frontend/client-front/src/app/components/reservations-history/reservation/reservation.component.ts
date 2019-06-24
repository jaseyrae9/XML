import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css', '../../../shared/css/inputField.css']
})
export class ReservationComponent implements OnInit {
  @Input() reservation = new ReservationPreview();
  @Output() resCanceled: EventEmitter<ReservationPreview> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }
  reservationCanceled(reservation: ReservationPreview) {
    console.log('Emitovanje');
    this.resCanceled.emit(reservation);
  }
}
