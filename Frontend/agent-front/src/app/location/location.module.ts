import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapDisplayComponent } from './components/map-display/map-display.component';
import { AddressDisplayComponent } from './components/address-display/address-display.component';

@NgModule({
  declarations: [MapDisplayComponent, AddressDisplayComponent],
  imports: [
    CommonModule
  ],
  exports: [
    AddressDisplayComponent,
    MapDisplayComponent
  ]
})
export class LocationModule { }
