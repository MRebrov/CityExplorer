/**
 * Класс квеста на frontend
 */
import {SpotDTO} from './spot.model';
import {User} from "../user/user.model";

export class QuestDTO {

  public questId: number;
  public name: string;
  public description: string;
  //public photo: File;
  public uploadDate: Date;
  public reward: number;
  public numberOfParticipants: number;
  // public lat:string;
  // public lng:string;
  public photoURL?: string;
  public spots: SpotDTO[];
  public cost: number;
  public numberOfJoiners: number;
  public owner: User;
  public reports: number;
  public status: number;

  constructor(name: string, description: string, uploadDate: Date, reward: number, numberOfParticipants: number, numberOfJoiners: number, status: number) {
    this.name = name;
    this.description = description;
   // this.photo = photo;
    this.uploadDate = uploadDate;
    this.reward = reward;
    this.numberOfParticipants = numberOfParticipants;
    this.numberOfJoiners = numberOfJoiners;
    this.spots=[];
    this.status = status;
  }
}
