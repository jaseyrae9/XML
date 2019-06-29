import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccommodationHTTPService {
  url = environment.baseUrl;
  hotelUrl = '/hotel/';
  roomUrl = '/room/';
  priceUrl = '/price/';
  roomTypeUrl = '/room_type/';
  roomCategoryUrl = '/room_category/';
  additionalServicesUrl  = '/additional_services/';
  imageUrl = '/room_image/'

  constructor(private http: HttpClient) { }

  getHotel(): Observable<any> {
    return this.http.get(this.url + this.hotelUrl);
  }

  getRooms(): Observable<any> {
    return this.http.get(this.url + this.roomUrl);
  }

  getRoom(roomId: string): Observable<Room> {
    return this.http.get<Room>(this.url + this.roomUrl + roomId);
  }

  createRoom(room): Observable<Room> {
    return this.http.post<Room>(this.url + this.roomUrl, room, environment.httpOptions);
  }

  setPrice(priceRequest): Observable<any> {
    return this.http.post(this.url + this.priceUrl, priceRequest, environment.httpOptions);
  }

  getPrices(roomId, from, to): Observable<any> {
    return this.http.get<Room>(this.url + this.priceUrl + '?room=' + roomId + '&date_after=' + from + '&date_before=' + to);
  }

  getRoomTypes(): Observable<any> {
    return this.http.get(this.url + this.roomTypeUrl);
  }

  getRoomCategories(): Observable<any> {
    return this.http.get(this.url + this.roomCategoryUrl);
  }

  getAdditionalServices(): Observable<any> {
    return this.http.get(this.url + this.additionalServicesUrl);
  }

  addImage(imageData: FormData): Observable<any> {
    return this.http.post(this.url + this.imageUrl, imageData);
  }
}
