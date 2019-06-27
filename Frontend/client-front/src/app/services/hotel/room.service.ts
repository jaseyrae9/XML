import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RoomService {

  url = 'http://localhost:8762/';

  constructor(private http: HttpClient) { }

  // room-service/room/id
  getRoom(id): Observable<any> {
    return this.http.get(this.url + 'room-service/room/' + id);
  }

  getPrices(id, from, to): Observable<any> {
    return this.http.get(this.url + 'room-service/room/' + id + '/price?from=' + from + '&to=' + to);
  }

}
