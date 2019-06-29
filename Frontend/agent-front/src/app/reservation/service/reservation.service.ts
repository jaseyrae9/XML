import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  reservationUrl = '/reservation/';
  roomUrl = '/room/';
  messageUrl = '/messages/';

  constructor(private http: HttpClient) { }

  addReservation(reservationInfo): Observable<any> {
    return this.http.post<Room>(environment.baseUrl + this.reservationUrl, reservationInfo, environment.httpOptions);
  }

  getReservations(roomId): Observable<any> {
    return this.http.get(environment.baseUrl + this.reservationUrl + '?room=' + roomId);
  }

  getReservation(reservationId): Observable<any> {
    return this.http.get(environment.baseUrl + this.reservationUrl +  reservationId);
  }

  approveReservation(reservationId): Observable<any> {
    return this.http.put<Room>(environment.baseUrl + this.reservationUrl + reservationId + '/', null, environment.httpOptions);
  }

  sendMessage(messageInfo): Observable<Message> {
    return this.http.post<Message>(environment.baseUrl + this.messageUrl, messageInfo, environment.httpOptions);
  }

  getMessages(reservationId): Observable<any> {
    return this.http.get(environment.baseUrl + this.messageUrl + '?reservationId=' + reservationId, environment.httpOptions);
  }

  getRoom(roomId): Observable<Room> {
    return this.http.get<Room>(environment.baseUrl + this.roomUrl + roomId);
  }
}
