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

const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/map', pathMatch: 'full'},
  {path: 'vk', component: VkComponent},
  {path: 'map', component: MapComponent},
  {path: 'userPage', component: UserPageComponent},
  {path: 'newquest', component: QuestComponent},
  {path: 'userquests', component: UserQuestListComponent},
  {path: 'quests', component: QuestListComponent},
  {path: 'questpage/:quest-id', component: QuestPageComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
