import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-display',
  templateUrl: './room-display.component.html',
  styleUrls: ['./room-display.component.css']
})
export class RoomDisplayComponent implements OnInit {
  room: Room = {} as Room;
  recensions: Recension[] = [];

  constructor(private route: ActivatedRoute, private accommodationHTTPService: AccommodationHTTPService) { }

  @ViewChild('galery') galery;

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.accommodationHTTPService.getRoom(id).subscribe(
      (data) => {
        this.room = data;
        this.galery.addImages(this.room.fotos);
        this.accommodationHTTPService.getRecensions(this.room.id).subscribe(
          (recensions) => {
            this.recensions = recensions.results;
          }
        );
      }
    );
  }

  uploadImage(image) {
    this.accommodationHTTPService.getRoom(this.room.id).subscribe(
      (data) => {
        this.galery.reloadImages(data.fotos);
      }
    );
  }

}
