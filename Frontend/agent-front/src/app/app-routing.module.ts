import { ReservationPageComponent } from './pages/reservation-page/reservation-page.component';
import { AllReservationsPageComponent } from './pages/all-reservations-page/all-reservations-page.component';
import { RoleGuardService } from './auth/service/route-guard.service';
import { CreateRoomPageComponent } from './pages/create-room-page/create-room-page.component';
import { RoomPageComponent } from './pages/room-page/room-page.component';
import { HotelPageComponent } from './pages/hotel-page/hotel-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';


const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'hotel', component: HotelPageComponent,
    canActivate: [RoleGuardService]
  },
  { path: 'room/:id', component: RoomPageComponent,
    canActivate: [RoleGuardService]
  },
  { path: 'room/:id/reservation', component: AllReservationsPageComponent,
    canActivate: [RoleGuardService]
  },
  { path: 'reservation/:id', component: ReservationPageComponent,
    canActivate: [RoleGuardService]
  },
  { path: 'createroom', component: CreateRoomPageComponent,
    canActivate: [RoleGuardService]
  }
];

export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(routes, {
  scrollPositionRestoration: 'enabled',
});
