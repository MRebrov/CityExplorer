import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterializeModule } from "angular2-materialize";

import { AppComponent } from './app.component';
import { RegistrationComponent} from "./registration/registration.component";
import {UserService} from "./registration/user.service";

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterializeModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
