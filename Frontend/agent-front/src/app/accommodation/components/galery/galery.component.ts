import { Component, OnInit, Input } from '@angular/core';
import { NgxGalleryImage, NgxGalleryOptions, NgxGalleryAnimation } from 'ngx-gallery';

@Component({
  selector: 'app-galery',
  templateUrl: './galery.component.html',
  styleUrls: ['./galery.component.css']
})
export class GaleryComponent implements OnInit {
  @Input() images = [];
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

  addImages(images) {
    if (this.images === undefined && images.length !== 0) {
      this.galleryImages = [];
    }
    this.images = images;
    this.images.forEach(image => {
      const galleryImage = {
        small: image.photo,
        medium: image.photo,
        big: image.photo
      };
      this.galleryImages.push(galleryImage);
    });
  }

  reloadImages(images) {
    this.galleryImages = [];
    this.images = images;
    this.images.forEach(image => {
      const galleryImage = {
        small: image.photo,
        medium: image.photo,
        big: image.photo
      };
      this.galleryImages.push(galleryImage);
    });
  }

}
