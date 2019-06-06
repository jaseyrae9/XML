import { Component, OnInit, Input} from '@angular/core';
import { RoomPreview } from 'src/app/model/room/roomPreview';

@Component({
  selector: 'app-room-basic-info',
  templateUrl: './room-basic-info.component.html',
  styleUrls: ['./room-basic-info.component.css']
})
export class RoomBasicInfoComponent implements OnInit {
  @Input() room: RoomPreview;

  constructor() { }

  ngOnInit() {
  }

}
