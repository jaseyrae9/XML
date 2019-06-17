import { LocationService } from './service/location.service';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapDisplayComponent } from './components/map-display/map-display.component';
import { AddressDisplayComponent } from './components/address-display/address-display.component';

@NgModule({
  declarations: [MapDisplayComponent, AddressDisplayComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [
    AddressDisplayComponent,
    MapDisplayComponent
  ],
  providers: [
    LocationService
  ]
})
export class LocationModule { }
