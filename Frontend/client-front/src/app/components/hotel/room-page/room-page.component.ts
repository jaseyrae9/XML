import { Component, OnInit, Input } from '@angular/core';
import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery';
import { RoomFull } from 'src/app/model/room/roomFull';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-room-page',
  templateUrl: './room-page.component.html',
  styleUrls: ['./room-page.component.css', '../../../shared/css/inputField.css']
})
export class RoomPageComponent implements OnInit {
  // galerija
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  @Input() room: RoomFull;

  // date picker - range
  datePickerConfig: Partial<BsDatepickerConfig>;
  bsRangeValue: Date[];

  constructor(public datePipe: DatePipe) { 
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });
  }

  ngOnInit() {
    this.galleryOptions = [
      {
          width: '250px',
          height: '200px',
          thumbnailsColumns: 4,
          imageAnimation: NgxGalleryAnimation.Slide
      },
      // max-width 800
      {
          breakpoint: 800,
          width: '100%',
          height: '600px',
          imagePercent: 80,
          thumbnailsPercent: 20,
          thumbnailsMargin: 10,
          thumbnailMargin: 10
      },
      // max-width 400
      {
          breakpoint: 400,
          preview: false
      }
  ];

    this.galleryImages = [
      {
          small: 'assets/images/room/queen/small.jpg',
          medium: 'assets/images/room/queen/medium.jpg',
          big: 'assets/images/room/queen/big.jpg'
      },
      {
        small: 'assets/images/room/king/small.jpg',
        medium: 'assets/images/room/king/medium.jpg',
        big: 'assets/images/room/king/big.jpg'
      },
      {
        small: 'assets/images/room/single/small.jpg',
        medium: 'assets/images/room/single/medium.jpg',
        big: 'assets/images/room/single/big.jpg'
      },
      {
        small: 'assets/images/room/double/small.jpg',
        medium: 'assets/images/room/double/medium.jpg',
        big: 'assets/images/room/double/big.jpg'
    },
  ];
  }

}
