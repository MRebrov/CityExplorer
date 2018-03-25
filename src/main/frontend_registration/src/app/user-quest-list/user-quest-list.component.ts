import { Component, OnInit } from '@angular/core';
import {QuestDTO} from "../quest/quest.model";
import {UserService} from "../user/user.service";
import {QuestService} from "../quest/quest.service";
import {Router} from "@angular/router";
import {UserProgressDTO} from "../quest/user-progress.model";

@Component({
  selector: 'app-user-quest-list',
  templateUrl: './user-quest-list.component.html',
  styleUrls: ['./user-quest-list.component.css']
})
export class UserQuestListComponent implements OnInit {

  userProgressList: UserProgressDTO[];

  constructor(private questService: QuestService, private router: Router) { }

  ngOnInit() {
    this.questService.getUserProgressForCurrentUser()
      .subscribe(
        (progresses: any[]) => {
          this.userProgressList = progresses;
          console.log('Progresses loaded successfully');
        },
        (error) => {
          console.log(error);
          this.router.navigate(['/login']);
        });
  }

}
