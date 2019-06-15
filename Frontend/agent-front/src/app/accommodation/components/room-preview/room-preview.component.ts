import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-room-preview',
  templateUrl: './room-preview.component.html',
  styleUrls: ['./room-preview.component.css']
})
export class RoomPreviewComponent implements OnInit {
  @Input() room: Room = {} as Room;
  constructor() { }

  ngOnInit() {
  }

}
