import { Injectable } from '@angular/core';
import { ChangePasswordData } from 'src/app/model/users/changePassword';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { User } from 'src/app/model/users/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCurrentUserProfile(): Observable<any> {
    return this.http.get('changeme/profile/info');
  }

}
