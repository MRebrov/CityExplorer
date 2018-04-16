import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {QuestDTO} from '../quest/quest.model';
import {UserProgressDTO} from '../quest/user-progress.model';
import {QuestService} from '../quest/quest.service';
import {Observable} from 'rxjs/Observable';
import {Status} from 'tslint/lib/runner';

@Component({
  selector: 'app-quest-page',
  templateUrl: './quest-page.component.html',
  styleUrls: ['./quest-page.component.css']
})
export class QuestPageComponent implements OnInit {
  private sub: any;
  quest: QuestDTO = new QuestDTO('', '', null, 0, 10);
  userProgress: UserProgressDTO = new UserProgressDTO(null, null);
  private photosToUpload: string[] = [];
  errorMsg: string;

  constructor(private route: ActivatedRoute, public questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.questService.getUserProgressByQuest(+params['quest-id'])
        .subscribe(
          (progress: any) => {
            this.userProgress = progress;
            this.userProgress.takingDate = new Date(this.userProgress.takingDate);
            if (this.userProgress.dateComplete != null)
              this.userProgress.dateComplete = new Date(this.userProgress.dateComplete);
          },
          (error) => {
            console.log(error);
          });

      this.questService.getQuestById(params['quest-id'])
        .subscribe(
          (quest: any) => {
            this.quest = quest;
            this.quest.uploadDate = new Date(this.quest.uploadDate);
          },
          (error) => {
            console.log(error);
            this.router.navigate(['/map']);
          });
    });
  }

  joinQuest() {
    this.questService.joinQuest(this.quest.questId).catch((response: Response) => {
      if (response.status == 401)
        this.router.navigate(['/login']);
      else
        this.writeError(response.text());
      return Observable.throw(response);
    })
      .subscribe(
        (response: any) => {
          console.log(response);
          location.reload();
        });
  }

  selectFile(event, spotId: number) {
    console.log('selected ' + spotId);
    this.questService.uploadSpotPhoto(event.target.files[0]).catch((response) => {
      return Observable.throw(response);
    }).subscribe((data) => {
        console.log(data);
        this.photosToUpload[spotId] = data;
      },
      (error) => {
        console.log(error);
      });
  }

  postPhoto(spotId: number) {
    if (this.photosToUpload[spotId] != null) {
      this.questService.postSpotPhoto(this.photosToUpload[spotId], this.quest.questId, spotId)
        .subscribe(
          (response: any) => {
            console.log(response);
            location.reload();
          },
          (error) => {
            console.log(error);
          });
    }
    else {
      console.log('no file loaded');
    }
  }

  writeError(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

}
