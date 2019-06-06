import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class HotelService {

  url = 'http://localhost:8762/';

  constructor(private http: HttpClient) {

  }

  getAll(pageNumer, sortBy): Observable<any> {
    return this.http.get(this.url + 'room-service/hotel?page='
                        + pageNumer + '&size=2&sort=' + sortBy);
  }

  getHotel(id): Observable<any> {
    return this.http.get(this.url + 'room-service/hotel/' + id);
  }
}
