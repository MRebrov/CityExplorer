import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {QuestDTO} from '../quest/quest.model';
import {UserProgressDTO} from '../quest/user-progress.model';
import {QuestService} from '../quest/quest.service';

@Component({
  selector: 'app-quest-page',
  templateUrl: './quest-page.component.html',
  styleUrls: ['./quest-page.component.css']
})
export class QuestPageComponent implements OnInit {
  private sub: any;
  quest: QuestDTO = new QuestDTO('','',null,0);
  userProgress: UserProgressDTO= new UserProgressDTO(null,null);

  constructor(private route: ActivatedRoute, public questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.questService.getUserProgressByQuestName(params['quest-name'])
        .subscribe(
          (progress: any) => {
            this.userProgress = progress;
          },
          (error) => {
            console.log(error);
          });

      this.questService.getQuestByName(params['quest-name'])
        .subscribe(
          (quest: any) => {
            this.quest = quest;
          },
          (error) => {
            console.log(error);
            this.router.navigate(['/map']);
          });
    });
  }

}
