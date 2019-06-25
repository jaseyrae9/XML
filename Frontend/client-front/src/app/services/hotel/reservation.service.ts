import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = 'http://localhost:8762/reservations-service/';

  constructor(private http: HttpClient) { }

  getRecensions(roomId): Observable<any> {
    return this.http.get(this.url + 'recension/approvedRecensions/' + roomId);
  }
  getReservations(): Observable<any> {
    return this.http.get(this.url + 'reservation');
  }

  getReservation(reservationId): Observable<any> {
    return this.http.get(this.url + 'reservation/' + reservationId);
  }

  cancelReservation(reservationId): Observable<any> {
    return this.http.delete(this.url + 'reservation/' + reservationId);
  }

  createReservation(reservationRequest ): Observable<any> {
    return this.http.post<ReservationPreview>(this.url + 'reservation', reservationRequest);
  }

}
