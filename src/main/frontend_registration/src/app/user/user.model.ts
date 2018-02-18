/**
 * Класс пользователя на frontend
 */
export class User {
  
  public firstName: string;
  public lastName: string;
  public birthday: string;
  public email: string;
  public password: string;

  constructor(firstName: string, lastName: string, birthday: string, email: string, password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
  }

}
