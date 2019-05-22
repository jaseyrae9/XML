import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { RoleGuardService } from 'src/app/auth/role-guard.service';
import { AllHotelsPageComponent } from './pages/all-hotels-page/all-hotels-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  //{ path: 'aircompanies', component: AllAirCompaniesPageComponent },
  //{ path: 'aircompany/:id', component: AirCompanyPageComponent },
  //{ path: 'flight/:id', component: FlightPageComponent },
  //{ path: 'find-flight', component: FlightSearchPageComponent },
  { path: 'hotels', component: AllHotelsPageComponent },
  //{ path: 'hotel/:id', component: HotelPageComponent },
  //{ path: 'find-room', component: RoomSearchPageComponent },
  //{ path: 'rent-a-car-companies', component: AllCarsCompaniesPageComponent },
  //{ path: 'rent-a-car-company/:id', component: CarCompanyPageComponent },
  //{ path: 'find-car', component: CarSearchPageComponent },
  //{ path: 'cars-and-hotels/:city/:date/:ticketCount', component: CarsRoomsFastComponent},
  /*{ path: 'reserve-flight/:id', component: ReserveFlightFormComponent ,
    canActivate: [RoleGuardService],
    data: {
      expectedRoles: ['CUSTOMER']
    }
  },*/
  /*{ path: 'history', component: AllReservationsComponent  ,
    canActivate: [RoleGuardService],
    data: {
      expectedRoles: ['CUSTOMER']
    }
  },*/
  /*{ path: 'invites', component: TripInvitesComponent  ,
    canActivate: [RoleGuardService],
    data: {
      expectedRoles: ['CUSTOMER']
    }
  },*/
  /*{
    path: 'profile', component: ProfileComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRoles: ['CUSTOMER', 'AIRADMIN', 'HOTELADMIN', 'CARADMIN', 'SYS']
    }
  },*/
  /*{
    path: 'friends', component: FriendsPageComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRoles: ['CUSTOMER']
    }
  },*/
  { path: 'error/:code', component: ErrorPageComponent },
  { path: '**', redirectTo: '' }
];

export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(routes, {
  scrollPositionRestoration: 'enabled',
});
