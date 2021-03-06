import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { QuestListComponent } from './quest/quest-list/quest-list.component';
import {LoaderService} from "./quest/loader.service";
import { UserQuestListComponent } from './user-quest-list/user-quest-list.component';
import { QuestPageComponent } from './quest-page/quest-page.component';
import { ConfirmationsListComponent } from './confirmations-list/confirmations-list.component';
import {LoginRedirectionService} from './login/login-redirection-service';
import { NotfoundComponent } from './notfound/notfound.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import {PasswordStrengthBarModule} from "ng2-password-strength-bar";
import {UserCurrentQuestListComponent} from './user-quest-list/current-quests/user-current-quest-list.component';
import {UserOwnedQuestListComponent} from './user-quest-list/owned-quests/user-owned-quest-list.component';
import { AdminComponent } from './admin/admin.component';
import {ChartsModule} from 'ng2-charts';
import {SecurityService} from "./security/security.service";
import {FinishGoogleAuthComponent} from './finish-google-auth/finish-google-auth.component';
import {Ng2SearchPipeModule} from "ng2-search-filter";
import {BootstrapSwitchModule} from "angular2-bootstrap-switch"
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { OffersComponent } from './offers/offers.component';
import { PurchasedOffersComponent } from './offers/purchased-offers/purchased-offers.component';
import { OwnedOffersComponent } from './offers/owned-offers/owned-offers.component';
import { CreateOfferComponent } from './create-offer/create-offer.component';
import { AvailableOffersComponent } from './offers/available-offers/available-offers.component'
import {OffersService} from './offers/offers.service';
import { SchedullersComponent } from './admin/schedullers/schedullers.component';
import { PanelComponent } from './admin/panel/panel.component';
import { ReportsComponent } from './admin/reports/reports.component';
import {SchedullerService} from "./admin/schedullers/scheduller.service";



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
    UserCurrentQuestListComponent,
    UserOwnedQuestListComponent,
    QuestListComponent,
    QuestPageComponent,
    FinishGoogleAuthComponent,
    ConfirmationsListComponent,
    NotfoundComponent,
    MainpageComponent,
    AdminComponent,
    OffersComponent,
    PurchasedOffersComponent,
    OwnedOffersComponent,
    CreateOfferComponent,
    AvailableOffersComponent,
    SchedullersComponent,
    PanelComponent,
    ReportsComponent,
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
    NgbModule.forRoot(),
    PasswordStrengthBarModule,
    ReactiveFormsModule,
    ChartsModule,
    Ng2SearchPipeModule,
    BootstrapSwitchModule.forRoot(),
    BrowserAnimationsModule
  ],
  bootstrap: [AppComponent],
  providers: [QuestService, UserService, LoaderService, LoginRedirectionService, SecurityService, OffersService, SchedullerService],
  //entryComponents: [InputFormComponent]
})
export class AppModule {
}
