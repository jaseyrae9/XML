import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../service/reservation.service';

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css']
})
export class ReservationDetailsComponent implements OnInit {
  reservation: Reservation = {} as Reservation;
  room: Room = {} as Room;
  messages: Message[] = [];
  newMessageForm: FormGroup;
  @ViewChild('sendMessageForm') sendMessageForm;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private reservationService: ReservationService) {
    this.newMessageForm = this.formBuilder.group({
      message: ['', [Validators.required]]
    });
   }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.reservationService.getMessages(id).subscribe(
      (data) => {
        this.messages = data.results;
      }
    );
    this.reservationService.getReservation(id).subscribe(
      (data) => {
        this.reservation = data;
        this.reservationService.getRoom(this.reservation.roomId).subscribe(
          (room) => {
            this.room = room;
          }
        );
      }
    );
  }

  approveReservation() {
    this.reservationService.approveReservation(this.reservation.id).subscribe(
      (data) => {
        this.reservation.status = 'HAPPENED';
      }
    );
  }

  sendMessage() {
    const messageInfo = {
      message: this.newMessageForm.value.message,
      reservationId: this.reservation.id
    };
    this.reservationService.sendMessage(messageInfo).subscribe(
      (data) => {
        console.log(data);
        this.sendMessageForm.resetForm();
        this.messages.push(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
