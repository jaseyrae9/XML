import { environment } from 'src/environments/environment';

import { Observable } from 'rxjs';
import { TokenService } from './../service/token.service';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private tokenService: TokenService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Interceptor')
        if (request.url.match(environment.baseUrl)) {
          const token = this.tokenService.getToken();
          if (token) {
              request = request.clone({
                  setHeaders: {
                      Authorization: `Bearer ${token}`
                  }
              });
          }
        }
        return next.handle(request);
    }
}
