import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AuthModule} from './auth/auth.module';
import {AppComponent} from './app.component';
import {RegistrationComponent} from './registration/registration.component';
import {AppRoutingModule} from './/app-routing.module';
import {LoginComponent} from './login/login.component';
import {VkComponent} from './vk/vk.component';
import {MapComponent} from './map/map.component';
import {AgmCoreModule} from '@agm/core';
import {UserPageComponent} from './user-page/user-page.component';
import {QuestComponent} from './quest/quest.component';
import {HttpClientModule} from '@angular/common/http';
import {QuestService} from './quest/quest.service';
import {HeaderComponent} from './header/header.component';
import {HeaderAuthorizedComponent} from './header/header-authorized/header-authorized.component';
import {HeaderUnauthorizedComponent} from './header/header-unauthorized/header-unauthorized.component';
import {UserService} from './user/user.service';
import { InputFormComponent } from './quest/input-form/input-form.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { QuestListComponent } from './quest/quest-list/quest-list.component';
import {LoaderService} from "./quest/loader.service";
import { UserQuestListComponent } from './user-quest-list/user-quest-list.component';
//import { InputFormComponent } from './quest/input-form/input-form.component';
//import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
//import { QuestListComponent } from './quest/quest-list/quest-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    VkComponent,
    MapComponent,
    UserPageComponent,
    QuestComponent,
    HeaderComponent,
    HeaderAuthorizedComponent,
    HeaderUnauthorizedComponent,
    UserQuestListComponent,
    //InputFormComponent,
    //QuestListComponent
    InputFormComponent,
    QuestListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AuthModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyB50v0zwR77xBBodMAILSKbUrqay6txODg',
    }),
    NgbModule.forRoot()
  ],
  bootstrap: [AppComponent],
  providers: [QuestService, UserService, LoaderService],
  //entryComponents: [InputFormComponent]
})
export class AppModule {
}
