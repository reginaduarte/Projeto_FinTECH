import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { BancoComponent } from './pages/trustbank/trustbank.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'trustbank', component: BancoComponent }
];