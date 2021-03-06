import { Component, OnInit, Input} from '@angular/core';
import { RoomPreview } from 'src/app/model/room/roomPreview';

@Component({
  selector: 'app-room-basic-info',
  templateUrl: './room-basic-info.component.html',
  styleUrls: ['./room-basic-info.component.css', '../../../shared/css/inputField.css']
})
export class RoomBasicInfoComponent implements OnInit {
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
