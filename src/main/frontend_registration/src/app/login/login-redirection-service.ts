import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

@Injectable()
export class LoginRedirectionService {
  private previousUrl : string = undefined;
  private currentUrl : string = undefined;

  constructor(private router: Router) {

  }

  public getPreviousUrl(){
    return this.previousUrl;
  }

  public init(){
    this.currentUrl = '/';
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.previousUrl = this.currentUrl;
        this.currentUrl = event.url;
      };
    });
  }
}
