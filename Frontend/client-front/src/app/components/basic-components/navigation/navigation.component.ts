import { Router } from '@angular/router';
import { Component, OnInit} from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  public loggedIn: Boolean;
  public username: String;

  constructor(private router: Router,
              public tokenService: TokenStorageService) {

    this.loggedIn = tokenService.checkIsLoggedIn();
    this.tokenService.logggedInEmitter.subscribe(loggedIn => {
      this.loggedIn = loggedIn;
    });
    this.username = tokenService.getUsername();
    this.tokenService.usernameEmitter.subscribe(username => this.username = username);

  }

  ngOnInit() {
  }

  logout() {
    this.router.navigateByUrl('changeme/');
    this.tokenService.signOut();
  }

}
