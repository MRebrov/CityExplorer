import {User} from "../user/user.model";
import {QuestDTO} from "./quest.model";


export class UserSpotProgressDTO {

  public id: number;
  public spotStatus: string;
  public dateComplete: Date;
  public spotId: number;

  constructor() {
  }
}
