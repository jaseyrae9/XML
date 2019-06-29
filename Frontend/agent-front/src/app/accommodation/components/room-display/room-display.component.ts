import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-display',
  templateUrl: './room-display.component.html',
  styleUrls: ['./room-display.component.css']
})
export class RoomDisplayComponent implements OnInit {
  room: Room = {} as Room;
  constructor(private route: ActivatedRoute, private accommodationHTTPService: AccommodationHTTPService) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.accommodationHTTPService.getRoom(id).subscribe(
      (data) => {
        this.room = data;
      }
    );
  }

}
