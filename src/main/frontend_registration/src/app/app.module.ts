import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthModule} from './/auth.module';
import { MaterializeModule } from 'angular2-materialize';
import { AppComponent } from './app.component';
import { RegistrationComponent} from './registration/registration.component';
import { AppRoutingModule } from './/app-routing.module';
import { LoginComponent } from './login/login.component';
import { VkComponent } from './vk/vk.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    VkComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AuthModule,
    HttpModule,
    MaterializeModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
