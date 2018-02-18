import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  exports: [ RouterModule ],
  imports:[RouterModule.forRoot(routes)]
})
export class AppRoutingModule {}
