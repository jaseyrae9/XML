import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  url = 'http://localhost:8762/';

  constructor(private http: HttpClient) { }

  getTypes(): Observable<any> {
    return this.http.get(this.url + 'admin-service/roomType');
  }

  getCategories(): Observable<any> {
    return this.http.get(this.url + 'admin-service/roomCategory');
  }
  getServices(): Observable<any> {
    return this.http.get(this.url + 'admin-service/additionalService');
  }

}
