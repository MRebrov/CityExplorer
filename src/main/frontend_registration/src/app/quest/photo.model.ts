import {User} from '../user/user.model';

export class PhotoDTO {

  public url:string;
  public uploadDate: Date;
  public photoType: string;
  public user: User;

  constructor(url: string, photoType: string, user: User) {
    this.url = url;
    this.photoType = photoType;
    this.user = user;
  }
}
