import { Component, OnInit, Input } from '@angular/core';
import { HotelPreview } from 'src/app/model/hotel/hotelPreview';

@Component({
  selector: 'app-hotel-basic-details',
  templateUrl: './hotel-basic-details.component.html',
  styleUrls: ['./hotel-basic-details.component.css']
})
export class HotelBasicDetailsComponent implements OnInit {
  @Input() hotel: HotelPreview = new HotelPreview();

  constructor() { }

  ngOnInit() {
  }

}
