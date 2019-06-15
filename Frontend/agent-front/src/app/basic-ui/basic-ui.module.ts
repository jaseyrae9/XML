import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [FooterComponent, NavbarComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    FooterComponent, NavbarComponent
  ]
})
export class BasicUiModule { }
