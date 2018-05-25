import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';
import {VkComponent} from './vk/vk.component';
import {MapComponent} from './map/map.component';
import {UserPageComponent} from './user-page/user-page.component';
import {QuestComponent} from './quest/quest.component';
import {UserQuestListComponent} from "./user-quest-list/user-quest-list.component";
import {QuestListComponent} from "./quest/quest-list/quest-list.component";
import {QuestPageComponent} from './quest-page/quest-page.component';
import {ConfirmationsListComponent} from './confirmations-list/confirmations-list.component';
import {MainpageComponent} from './mainpage/mainpage.component';
import {NotfoundComponent} from './notfound/notfound.component';
import {AdminComponent} from "./admin/admin.component";
import {FinishGoogleAuthComponent} from './finish-google-auth/finish-google-auth.component';
import {OffersComponent} from './offers/offers.component';
import {CreateOfferComponent} from './create-offer/create-offer.component';



const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'login/:state', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  // {path: '', redirectTo: '/map', pathMatch: 'full'},
  {path: 'vk', component: VkComponent},
  {path: 'map', component: MapComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'userPage', component: UserPageComponent},
  {path: 'newquest', component: QuestComponent},
  {path: 'userquests', component: UserQuestListComponent},
  {path: 'quests', component: QuestListComponent},
  {path: 'offers', component: OffersComponent},
  {path: 'newoffer', component: CreateOfferComponent},
  {path: 'confirmations', component: ConfirmationsListComponent},
  {path: 'questpage/:quest-id', component: QuestPageComponent},
  {path: 'finishgoogleauth/:token', component: FinishGoogleAuthComponent},
  {path: 'mainpage', component: MainpageComponent},
  {path: '', redirectTo: '/mainpage', pathMatch: 'full'},
  {path: 'notfound', component: NotfoundComponent},
  {path: '**', component: NotfoundComponent },

];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
