import { CreateRoomPageComponent } from './pages/create-room-page/create-room-page.component';
import { RoomPageComponent } from './pages/room-page/room-page.component';
import { HotelPageComponent } from './pages/hotel-page/hotel-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';


const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'hotel', component: HotelPageComponent },
  { path: 'createroom', component: CreateRoomPageComponent },
  { path: 'room/:id', component: RoomPageComponent },
  { path: '**', redirectTo: '' }
];

export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(routes, {
  scrollPositionRestoration: 'enabled',
});
