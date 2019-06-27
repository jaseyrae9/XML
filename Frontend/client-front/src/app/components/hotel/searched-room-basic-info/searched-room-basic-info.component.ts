import { Component, OnInit, Input } from '@angular/core';
import { RoomPreview } from 'src/app/model/room/roomPreview';

@Component({
  selector: 'app-searched-room-basic-info',
  templateUrl: './searched-room-basic-info.component.html',
  styleUrls: ['./searched-room-basic-info.component.css', '../../../shared/css/inputField.css']
})
export class SearchedRoomBasicInfoComponent implements OnInit {
  @Input() room: RoomPreview;

  constructor() { }

  ngOnInit() {
  }

}
