import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccommodationHTTPService {
  url = 'http://localhost:8000';
  hotelUrl = '/hotel/';
  roomUrl = '/room/';
  priceUrl = '/price/';
  roomTypeUrl = '/room_type/';
  roomCategoryUrl = '/room_category/';
  additionalServicesUrl  = '/additional_services/';

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
    return this.http.post<Room>(this.url + this.roomUrl, room, httpOptions);
  }

  setPrice(priceRequest): Observable<any> {
    return this.http.post(this.url + this.priceUrl, priceRequest, httpOptions);
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
}
