import {User} from '../user/user.model';

export class PhotoDTO {

  public url:string;
  public uploadDate: Date;
  public photoType: string;

  constructor(url: string, photoType: string) {
    this.url = url;
    this.photoType = photoType;
  }
}
