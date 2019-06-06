import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { HotelService } from 'src/app/services/hotel/hotel.service';
import { HotelPreview } from 'src/app/model/hotel/hotelPreview';

@Component({
  selector: 'app-all-hotels-page',
  templateUrl: './all-hotels-page.component.html',
  styleUrls: ['./all-hotels-page.component.css', '../../shared/css/inputField.css']
})
export class AllHotelsPageComponent implements OnInit {
  hotels: HotelPreview[] = [];
  sort  = 'name';
  pages: Array<number> = new Array();
  pageNumber = 0;

  modalRef: BsModalRef;

  constructor(private hotelService: HotelService) { }

  ngOnInit() {
    this.loadHotels();
  }

  onSortChange(value) {
    this.sort = value;
    this.loadHotels();
  }

    loadHotels() {
    this.hotelService.getAll(this.pageNumber, this.sort).subscribe(data => {
      console.log(data);
      this.hotels = data['content'];
      this.pages = new Array(data['totalPages']);
    });
  }

  arrowAction(i: number, event: any) {
  if ( this.pageNumber + i >= 0 && this.pageNumber + i < this.pages.length) {
    this.pageNumber += i;
    this.loadHotels();
  }
}
  changePage(i: number, event: any) {
    this.pageNumber = i;
    this.loadHotels();
  }

}
