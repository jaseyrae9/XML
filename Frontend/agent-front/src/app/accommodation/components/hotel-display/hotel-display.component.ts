import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hotel-display',
  templateUrl: './hotel-display.component.html',
  styleUrls: ['./hotel-display.component.css']
})
export class HotelDisplayComponent implements OnInit {
  hotel: Hotel = {address: {}, name: '', pib: ''} as Hotel;
  rooms: Room[] = [];

  constructor(private accommodationService: AccommodationHTTPService) { }

  ngOnInit() {
    this.accommodationService.getHotel().subscribe(
        (hotels) => {
          this.hotel = hotels.results[0];
        }
    );
    this.accommodationService.getRooms().subscribe(
        (rooms) => {
          this.rooms = rooms.results;
        }
    );
  }
}
