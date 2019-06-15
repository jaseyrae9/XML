import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccommodationHTTPService {
  url = 'http://localhost:8000';
  hotelUrl = '/hotel';
  roomUrl = '/room';

  constructor(private http: HttpClient) { }

  getHotel(): Observable<any> {
    return this.http.get(this.url + this.hotelUrl);
  }

  getRooms(): Observable<any>{
    return this.http.get(this.url + this.roomUrl);
  }

  getRoom(roomId: string): Observable<Room> {
    return this.http.get<Room>(this.url + this.roomUrl + '/' + roomId);
  }
}
