import { Message } from 'src/app/model/reservation/message';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { RoomFull } from 'src/app/model/room/roomFull';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';
import { RoomService } from 'src/app/services/hotel/room.service';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from 'src/app/services/hotel/reservation.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationRequest } from 'src/app/model/reservation/reservationRequest';
import { Router } from '@angular/router';
import { Recension } from 'src/app/model/reservation/recension';

@Component({
  selector: 'app-room-page',
  templateUrl: './room-page.component.html',
  styleUrls: ['./room-page.component.css', '../../../shared/css/inputField.css']
})
export class RoomPageComponent implements OnInit {

  @Input() roomFull: RoomFull = new RoomFull();
  recensions: Recension[] = [];
  errorMessage = '';

  averageRating = 0;
  roomId;

  // date picker - range
  datePickerConfig: Partial<BsDatepickerConfig>;
  bsRangeValue: Date[];

  bookForm: FormGroup;

  reservationRequest: ReservationRequest = new ReservationRequest();

  @ViewChild('gallery') gallery;
  @ViewChild('prices') prices;

  constructor(public datePipe: DatePipe,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private reservationService: ReservationService,
              private formBuilder: FormBuilder,
              private router: Router) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });
  }

  ngOnInit() {
    this.roomId = this.route.snapshot.paramMap.get('id');
    this.loadRoom();
    this.loadRecensions();
    this.bookForm = this.formBuilder.group({
      bsRangeValue: [this.bsRangeValue, [Validators.required]]
    });
  }

  loadRoom() {
    console.log('loading room');
    this.roomService.getRoom(this.roomId).subscribe(
      (data) => {
        this.roomFull = data;
        this.gallery.populateGallery(this.roomFull.images);
        this.prices.removeUndefined(this.roomFull.defaultPrice);
      }, (error) => {
        this.errorMessage = error.error.message;
      }
    );
  }

  loadRecensions() {
    this.reservationService.getRecensions(this.roomId).subscribe(
      (data) => {
        this.recensions = data;
        console.log(data);
      }
    );
  }

  bookRoom() {
    const start = new Date(this.bookForm.value.bsRangeValue[0]);
    const end = new Date(this.bookForm.value.bsRangeValue[1]);

    this.reservationRequest.start = this.datePipe.transform(start, 'yyyy-MM-dd');
    this.reservationRequest.end = this.datePipe.transform(end, 'yyyy-MM-dd');
    this.reservationRequest.roomId = this.roomId;
    console.log(this.reservationRequest);
    this.reservationService.createReservation(this.reservationRequest).subscribe(
      (data) => {
        this.router.navigate(['/history']);
      },
      (error) => {
        this.errorMessage = error.error.message;
      }

    );

  }

}
