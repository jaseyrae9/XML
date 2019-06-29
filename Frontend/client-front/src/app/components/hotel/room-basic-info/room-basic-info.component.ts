import { Component, OnInit, Input} from '@angular/core';
import { RoomPreview } from 'src/app/model/room/roomPreview';

@Component({
  selector: 'app-room-basic-info',
  templateUrl: './room-basic-info.component.html',
  styleUrls: ['./room-basic-info.component.css', '../../../shared/css/inputField.css']
})
export class RoomBasicInfoComponent implements OnInit {
  @Input() room: RoomPreview;

  url = 'assets/images/room/queen/medium.jpg';

  constructor() { }

  ngOnInit() {
    const uints = new Uint8Array(this.room.mainImage);
    const stringChar = String.fromCharCode.apply(null, uints);

    const base64 = btoa(String.fromCharCode(stringChar));
    this.url = 'data:image/jpeg;base64,' + base64; // use t
    console.log(this.url);
  }

}
