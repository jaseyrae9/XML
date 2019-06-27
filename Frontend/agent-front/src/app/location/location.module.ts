import { LocationService } from './service/location.service';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapDisplayComponent } from './components/map-display/map-display.component';
import { AddressDisplayComponent } from './components/address-display/address-display.component';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [MapDisplayComponent, AddressDisplayComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAha_PuYkkB226RBgsn81j3CP7vG-Mv1ig'
    })
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
