import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { RoleGuardService } from 'src/app/auth/role-guard.service';
import { AllHotelsPageComponent } from './pages/all-hotels-page/all-hotels-page.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { AllReservationsComponent } from './components/reservations-history/all-reservations/all-reservations.component';
import { HotelPageComponent } from './components/hotel/hotel-page/hotel-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'hotels', component: AllHotelsPageComponent },
  { path: 'hotel/:id', component: HotelPageComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'history', component: AllReservationsComponent},
  { path: 'find-hotel', component: SearchPageComponent },
  { path: 'error/:code', component: ErrorPageComponent },
  { path: '**', redirectTo: '' }
];

export const RoutingModule: ModuleWithProviders = RouterModule.forRoot(routes, {
  scrollPositionRestoration: 'enabled',
});
