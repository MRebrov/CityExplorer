import {User} from "../user/user.model";
import {QuestDTO} from "./quest.model";


export class UserProgressDTO {

  public user: User;
  public quest: QuestDTO;
  public dateComplete: Date;
  public takingDate: Date;

  constructor(user: User, quest: QuestDTO) {
    this.user = user;
    this.quest = quest;
  }
}
