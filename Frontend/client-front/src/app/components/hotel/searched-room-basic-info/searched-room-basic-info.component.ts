import { RoomPreview } from 'src/app/model/room/roomPreview';
import { Component, OnInit, Input } from '@angular/core';
import { RoomFull } from 'src/app/model/room/roomFull';

@Component({
  selector: 'app-searched-room-basic-info',
  templateUrl: './searched-room-basic-info.component.html',
  styleUrls: ['./searched-room-basic-info.component.css', '../../../shared/css/inputField.css']
})
export class SearchedRoomBasicInfoComponent implements OnInit {
  @Input() room: RoomPreview;
  url = 'assets/images/no-image.png';

  constructor() { }

  ngOnInit() {
    if (this.room.mainImage) {
      const base64 = window.atob(this.room.mainImage);
      this.url = 'data:image/jpeg;base64,' + base64;
    }
  }

}
