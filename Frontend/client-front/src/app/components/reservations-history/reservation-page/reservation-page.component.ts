import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from 'src/app/services/hotel/reservation.service';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';
import { DatePipe } from '@angular/common';
import { Message } from 'src/app/model/reservation/message';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageRequest } from 'src/app/model/reservation/messageRequest';

@Component({
  selector: 'app-reservation-page',
  templateUrl: './reservation-page.component.html',
  styleUrls: ['./reservation-page.component.css', '../../../shared/css/inputField.css']
})
export class ReservationPageComponent implements OnInit {

  reservationId;
  @Input() reservation: ReservationPreview = new ReservationPreview();
  createdOn;
  errorMessage = '';
  @ViewChild('sendMessageForm') sendMessageForm;

  messages: Message[] = [];
  newMessageForm: FormGroup;
  messageRequest: MessageRequest = new MessageRequest();

  constructor(public datePipe: DatePipe,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private reservationService: ReservationService) {
    this.newMessageForm = this.formBuilder.group({
      message: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.reservationId = this.route.snapshot.paramMap.get('id');
    this.loadReservation();
    this.loadMessages();
  }

  loadReservation() {
    this.reservationService.getReservation(this.reservationId).subscribe(
      (data) => {
        this.reservation = data;
        // this.createdOn = this.datePipe.transform(this.reservation.created, 'yyyy-MM-dd');
       // console.log('Rezervacija', data);
      },
      (error) => {
        this.errorMessage = error.error.message;
      }
    );
  }

  loadMessages() {
    this.reservationService.getMessages(this.reservationId).subscribe(
      (data) => {
        this.messages = data.content;
        console.log('Poruke: ', this.messages);
      }
    );
  }


  sendMessage() {
    this.messageRequest.message =  this.newMessageForm.value.message;
    this.reservationService.sendMessage(this.reservationId, this.messageRequest).subscribe(
      (data) => {
        console.log(data);
        this.sendMessageForm.resetForm();
        this.messages.push(data);
      },
      (error) => {
        this.errorMessage = error.error.message;
      }
    );
  }

  recensionCreated(data) {
    this.reservation.hasRecension = true;
  }
}
