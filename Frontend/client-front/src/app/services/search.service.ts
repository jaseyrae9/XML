import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RoomPreview } from '../model/room/roomPreview';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  url = environment.baseUrl;


  constructor(private http: HttpClient) { }

  getTypes(): Observable<any> {
    return this.http.get(this.url + '/admin-service/roomType');
  }

  getCategories(): Observable<any> {
    return this.http.get(this.url + '/admin-service/roomCategory');
  }
  getServices(): Observable<any> {
    return this.http.get(this.url + '/admin-service/additionalService');
  }

  search(searchRequest): Observable<any> {
    return this.http.post<RoomPreview[]>(this.url + '/search-service/search', searchRequest);
  }
}
