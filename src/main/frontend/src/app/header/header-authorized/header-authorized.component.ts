import {Component, OnInit} from '@angular/core';
import {UserService} from '../../user/user.service';
import {Router} from '@angular/router';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {QuestService} from '../../quest/quest.service';

@Component({
  selector: 'app-header-authorized',
  templateUrl: './header-authorized.component.html',
  styleUrls: ['./header.aut.component.css'],
  providers: []
})
export class HeaderAuthorizedComponent implements OnInit {


  constructor(private userService: UserService, public questService:QuestService, private router: Router) {
  }

  ngOnInit() {

  }

  logout() {
    this.userService.logout().subscribe((obj: any) => {
      this.questService.removeLoadedConfirmations();
      this.router.navigate(['/']);
    });

  }
}
