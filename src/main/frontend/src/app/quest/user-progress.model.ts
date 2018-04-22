import {User} from "../user/user.model";
import {QuestDTO} from "./quest.model";
import {UserSpotProgressDTO} from './user-spot-progress.model';


export class UserProgressDTO {

  public user: User;
  public quest: QuestDTO;
  public dateComplete: Date;
  public takingDate: Date;
  public userSpotProgresses: UserSpotProgressDTO[];

  constructor(user: User, quest: QuestDTO) {
    this.user = user;
    this.quest = quest;
  }
}
