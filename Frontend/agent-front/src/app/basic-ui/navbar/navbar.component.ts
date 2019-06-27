import { TokenService } from './../../auth/service/token.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
  }

  onLogout(){
    this.tokenService.signOut();
  }

}
