import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-finish-google-auth',
  template: ''
})
export class FinishGoogleAuthComponent implements OnInit {
  private sub: any;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    if (this.userService._isAuthenticatedSubject.getValue()) {
      alert('You are authorized! You should log out first');
      this.router.navigate(['/map']);
    }

    this.sub = this.route.params.subscribe(params => {
      this.userService.authorizeViaGoogle(params['token']);
      this.router.navigate(['/map']);
    });
  }


}
