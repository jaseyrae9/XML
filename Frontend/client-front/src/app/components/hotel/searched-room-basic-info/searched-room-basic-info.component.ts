import { Component, OnInit, Input } from '@angular/core';
import { RoomFull } from 'src/app/model/room/roomFull';

@Component({
  selector: 'app-searched-room-basic-info',
  templateUrl: './searched-room-basic-info.component.html',
  styleUrls: ['./searched-room-basic-info.component.css', '../../../shared/css/inputField.css']
})
export class SearchedRoomBasicInfoComponent implements OnInit {
  @Input() room: RoomFull;

  constructor() { }

  ngOnInit() {
  }

}
