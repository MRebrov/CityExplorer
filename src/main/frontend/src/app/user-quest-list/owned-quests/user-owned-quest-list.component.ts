import {Component, OnInit} from '@angular/core';
import {QuestDTO} from '../../quest/quest.model';
import {UserService} from '../../user/user.service';
import {QuestService} from '../../quest/quest.service';
import {Router} from '@angular/router';
import {UserProgressDTO} from '../../quest/user-progress.model';

@Component({
  selector: 'app-user-owned-quest-list',
  templateUrl: './user-owned-quest-list.component.html',
  styleUrls: ['./user-owned-quest-list.component.css']
})
export class UserOwnedQuestListComponent implements OnInit {

  ownedQuests: QuestDTO[];

  constructor(private questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    this.questService.getQuestsByOwner()
      .subscribe(
        (quests: any[]) => {
          this.ownedQuests = quests;
          for (let quest of this.ownedQuests) {
            quest.uploadDate = new Date(quest.uploadDate);
          }
          this.ownedQuests.sort((a, b) => {
            if (a.uploadDate > b.uploadDate)
              return -1;
            else
              return 1;
          });
          console.log('Owned quests loaded successfully');
        },
        (error) => {
          console.log(error);
          alert('Seems like you are not authorized! Please sign in first');
          this.router.navigate(['/login']);
        });
  }

}
