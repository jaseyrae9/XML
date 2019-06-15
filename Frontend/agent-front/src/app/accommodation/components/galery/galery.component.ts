import { Component, OnInit } from '@angular/core';
import { NgxGalleryImage, NgxGalleryOptions, NgxGalleryAnimation } from 'ngx-gallery';

@Component({
  selector: 'app-galery',
  templateUrl: './galery.component.html',
  styleUrls: ['./galery.component.css']
})
export class GaleryComponent implements OnInit {
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  constructor() { }

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
