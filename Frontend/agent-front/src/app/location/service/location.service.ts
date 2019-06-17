import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  url = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
  key = ',+CA&key=AIzaSyDxMEmiWGtvuSRvzBShDgvPbWYQgo3GEHQ';

  constructor(private http: HttpClient) { }

  geocodeAddress(address: Address): Observable<any>{
    const addressString = address.street + '+' + address.streetNumber + '+' + address.city + '+' + address.country;
    return this.http.get(this.url + addressString + this.key);
  }
}
