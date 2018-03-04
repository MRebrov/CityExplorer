/**
 * Класс квеста на frontend
 */
export class Quest {

  public name: string;
  public description: string;
  public photo: File;
  public uploadDate: string;
  public reward: number;
  public posLat:number;
  public posLng:number;


  constructor(name: string, description: string, photo: File, uploadDate: string, reward: number, posLat: number, posLng: number) {
    this.name = name;
    this.description = description;
    this.photo = photo;
    this.uploadDate = uploadDate;
    this.reward = reward;
    this.posLat = posLat;
    this.posLng = posLng;
  }
}
