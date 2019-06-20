import { Component, OnInit, Input } from '@angular/core';
import { RoomFull } from 'src/app/model/room/roomFull';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';
import { RoomService } from 'src/app/services/hotel/room.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-page',
  templateUrl: './room-page.component.html',
  styleUrls: ['./room-page.component.css', '../../../shared/css/inputField.css']
})
export class RoomPageComponent implements OnInit {

  @Input() roomFull: RoomFull = new RoomFull();
  roomId;

  // date picker - range
  datePickerConfig: Partial<BsDatepickerConfig>;
  bsRangeValue: Date[];

  constructor(public datePipe: DatePipe,
              private route: ActivatedRoute,
              private roomService: RoomService) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });
  }

  ngOnInit() {
    this.roomId = this.route.snapshot.paramMap.get('id');
    this.loadRoom();
  }

  loadRoom() {
    this.roomService.getRoom(this.roomId).subscribe(
      (data) => {
        this.roomFull = data;
      }
    );
  }

}
