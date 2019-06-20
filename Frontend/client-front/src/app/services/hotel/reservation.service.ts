import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = 'http://localhost:8762/reservations-service/';

  constructor(private http: HttpClient) { }

  getRecensions(roomId): Observable<any> {
    return this.http.get(this.url + 'recension/approvedRecensions/' + roomId);
  }

}
