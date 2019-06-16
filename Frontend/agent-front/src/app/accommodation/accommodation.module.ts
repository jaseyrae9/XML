import { RouterModule } from '@angular/router';
import { LocationModule } from './../location/location.module';
import { HttpClientModule } from '@angular/common/http';
import { NgxGalleryModule } from 'ngx-gallery';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HotelDisplayComponent } from './components/hotel-display/hotel-display.component';
import { RoomPreviewComponent } from './components/room-preview/room-preview.component';
import { RoomDisplayComponent } from './components/room-display/room-display.component';
import { GaleryComponent } from './components/galery/galery.component';

@NgModule({
  declarations: [HotelDisplayComponent, RoomPreviewComponent, RoomDisplayComponent, GaleryComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    NgxGalleryModule,
    LocationModule
  ],
  exports: [
    HotelDisplayComponent, RoomDisplayComponent
  ]
})
export class AccommodationModule { }
