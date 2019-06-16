import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-display',
  templateUrl: './room-display.component.html',
  styleUrls: ['./room-display.component.css']
})
export class RoomDisplayComponent implements OnInit {
  @Input() room: Room = {} as Room;
  constructor(private accommodationService: AccommodationHTTPService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.accommodationService.getRoom(id).subscribe(
      (room: Room) => {
        this.room = room;
      }
    )
  }

}
