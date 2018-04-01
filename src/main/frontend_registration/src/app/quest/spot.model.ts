import {PhotoDTO} from './photo.model';

export class SpotDTO {
  public spotId: number;
  public name: string;
  public uploadDate: Date;
  public lat: string;
  public lng: string;
  public photos: PhotoDTO[];
  public mainPhoto: PhotoDTO;

  constructor(name: string, lat: string, lng: string) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }
}
