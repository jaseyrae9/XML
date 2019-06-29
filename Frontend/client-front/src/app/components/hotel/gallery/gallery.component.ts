import { Component, OnInit } from '@angular/core';
import { NgxGalleryImage, NgxGalleryOptions, NgxGalleryAnimation } from 'ngx-gallery';
import { TouchSequence } from 'selenium-webdriver';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
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
          small: 'assets/images/no-image.png',
          medium: 'assets/images/no-image.png',
          big: 'assets/images/no-image.png'
      }
    ];
  }

  populateGallery(images) {
    if (images.length !== 0) {
      this.galleryImages = [];
      images.forEach(image => {
        const base64 = window.atob(image);
        const url = 'data:image/jpeg;base64,' + base64;
        const newImage = {
          small: url,
          medium: url,
          big: url
        };
        this.galleryImages.push(newImage);
      });
    }
  }
}
