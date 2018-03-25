/**
 * Класс квеста на frontend
 */
import {SpotDTO} from './spot.model';

export class QuestDTO {

  public name: string;
  public description: string;
  //public photo: File;
  public uploadDate: Date;
  public reward: number;
  // public lat:string;
  // public lng:string;
  public photoURL?: string;
  public spots: SpotDTO[];

  constructor(name: string, description: string, uploadDate: Date, reward: number) {
    this.name = name;
    this.description = description;
   // this.photo = photo;
    this.uploadDate = uploadDate;
    this.reward = reward;
  }
}
