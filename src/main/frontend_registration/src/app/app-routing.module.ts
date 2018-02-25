import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {VkComponent} from "./vk/vk.component";
import {MapComponent} from "./map/map.component";

const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'vk', component: VkComponent },
  {path: 'map', component: MapComponent},
];

@NgModule({
  exports: [ RouterModule ],
  imports:[RouterModule.forRoot(routes)]
})
export class AppRoutingModule {}
