
export class VkUser {

  public firstName: string;
  public lastName: string;
  public photoUrl: string;
  public city: string;

  constructor(firstName: string, lastName: string, photoUrl: string, city: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.photoUrl = photoUrl;
    this.city = city;
  }

}
