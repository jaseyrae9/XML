import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReservationPreview } from 'src/app/model/reservation/reservationPreview';
import { Recension } from 'src/app/model/reservation/recension';
import { Message } from 'src/app/model/reservation/message';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getRecensions(roomId): Observable<any> {
    return this.http.get(this.url + '/reservations-service/recension/approvedRecensions/' + roomId);
  }
  getReservations(): Observable<any> {
    return this.http.get(this.url + '/reservations-service/reservation');
  }

  getReservation(reservationId): Observable<any> {
    return this.http.get(this.url + '/reservations-service/reservation/' + reservationId);
  }

  cancelReservation(reservationId): Observable<any> {
    return this.http.delete(this.url + '/reservations-service/reservation/' + reservationId);
  }

  createReservation(reservationRequest ): Observable<any> {
    return this.http.post<ReservationPreview>(this.url + '/reservations-service/reservation', reservationRequest);
  }

  postRecension(recension): Observable<any>  {
    return this.http.post<Recension>(this.url + '/reservations-service/recension', recension);
  }

  sendMessage(reservationId, messageRequest): Observable<any> {
    return this.http.post<Message>(this.url + '/reservations-service/reservation/' + reservationId + '/message', messageRequest);
  }
  getMessages(reservationId): Observable<any> {
    return this.http.get(this.url + '/reservations-service/reservation/' + reservationId + '/message');
  }
}
