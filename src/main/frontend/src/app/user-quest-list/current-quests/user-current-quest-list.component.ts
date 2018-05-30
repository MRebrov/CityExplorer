import {Component, OnInit} from '@angular/core';
import {QuestDTO} from '../../quest/quest.model';
import {UserService} from '../../user/user.service';
import {QuestService} from '../../quest/quest.service';
import {Router} from '@angular/router';
import {UserProgressDTO} from '../../quest/user-progress.model';

@Component({
  selector: 'app-user-current-quest-list',
  templateUrl: './user-current-quest-list.component.html',
  styleUrls: ['./user-current-quest-list.component.css']
})
export class UserCurrentQuestListComponent implements OnInit {

  userProgressList: UserProgressDTO[];
  loading: boolean = false;

  constructor(private questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    this.loading = true;
    this.questService.getUserProgressForCurrentUser()
      .subscribe(
        (progresses: any[]) => {
          this.userProgressList = progresses;
          this.loading = false;
          for (let userProgress of this.userProgressList) {
            if (userProgress.dateComplete != null)
              userProgress.dateComplete = new Date(userProgress.dateComplete);
            userProgress.takingDate = new Date(userProgress.takingDate);
          }
          this.userProgressList.sort((a, b) => {
            if (a.dateComplete == null && b.dateComplete != null)
              return -1;
            else if (a.dateComplete != null && b.dateComplete == null)
              return 1;
            else {
              if (a.takingDate > b.takingDate)
                return -1;
              else
                return 1;
            }
          });
          console.log('Progresses loaded successfully');
        },
        (error) => {
          console.log(error);
          this.loading = false;
          alert('Seems like you are not authorized! Please sign in first');
          this.router.navigate(['/login']);
        });
  }

}
