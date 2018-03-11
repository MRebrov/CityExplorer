/**
 * Класс квеста на frontend
 */
export class Quest {

  public name: string;
  public description: string;
  //public photo: File;
  public uploadDate: Date;
  public reward: number;
  public lat:string;
  public lng:string;


  constructor(name: string, description: string, uploadDate: Date, reward: number, posLat: string, posLng: string) {
    this.name = name;
    this.description = description;
   // this.photo = photo;
    this.uploadDate = uploadDate;
    this.reward = reward;
    this.lat = posLat;
    this.lng = posLng;
  }
}
