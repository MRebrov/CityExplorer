import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {VkComponent} from "./vk/vk.component";

const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'vk', component: VkComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports:[RouterModule.forRoot(routes)]
})
export class AppRoutingModule {}
