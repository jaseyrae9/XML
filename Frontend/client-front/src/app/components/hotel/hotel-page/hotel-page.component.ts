import { Component, OnInit } from '@angular/core';
import { HotelFull } from 'src/app/model/hotel/hotelFull';
import { HotelService } from 'src/app/services/hotel/hotel.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotel-page',
  templateUrl: './hotel-page.component.html',
  styleUrls: ['./hotel-page.component.css', '../../../shared/css/inputField.css']
})
export class HotelPageComponent implements OnInit {

  hotelFull: HotelFull = new HotelFull();
  hotelId;

  constructor(private route: ActivatedRoute,
              private hotelService: HotelService) { }

  ngOnInit() {
    this.hotelId = this.route.snapshot.paramMap.get('id');
    this.loadHotel();
  }

  loadHotel() {
    this.hotelService.getHotel(this.hotelId).subscribe(
      (data) => {
        this.hotelFull = data;
        console.log('Hotel: ' + data.name);
      }
    );
  }
}
