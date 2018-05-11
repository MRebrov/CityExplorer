import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {QuestDTO} from './quest.model';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {AuthHttp} from 'angular2-jwt';
import {SpotDTO} from './spot.model';
import {UserProgressDTO} from './user-progress.model';
import {SpotConfirmationDTO} from '../confirmations-list/spot-confirmation.model';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class QuestService {

  private quests: QuestDTO[] = null;
  private confirmations: SpotConfirmationDTO[] = null;

  public _confirmationsCountSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public confirmationsCountObs: Observable<number> = this._confirmationsCountSubject.asObservable();

  constructor(public authHttp: AuthHttp, public http: Http) {
  }


  //
  // loadFile(file: File):Observable<HttpEvent<{}>>{
  //   let formdata: FormData = new FormData();
  //
  //   formdata.append('file', file);
  //
  //   const req = new HttpRequest('POST', '/upload-photo', formdata, {
  //     reportProgress: true,
  //     responseType: 'text'
  //   });
  //
  //   return this.http.request(req);
  // }

  getQuests() {
    return this.http.get('userapi/get-quests').map((response: Response) => {
      return response.json();
    });
  }

  postPhoto(file: File, quest: QuestDTO) {
    let formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('quest', JSON.stringify(quest));
    return this.http.post('userapi/upload-photo', formdata).map(res => res.text());
  }

  uploadSpotPhoto(file: File) {
    let formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post('userapi/upload-photo', formdata).map(res => res.text());
  }

  postQuestInfo(quest: QuestDTO) {
    return this.authHttp.post('userapi/upload-info', quest).map(res => res.text());
  }

  // loadQuestInfo(quest:QuestDTO){
  //   const req = new HttpRequest('POST', 'userapi/add-quest', quest, {
  //     responseType: 'text'
  //   });
  //   return this.http.request(req);
  // }

  loadQuestsInRange(lat: number, lng: number, range: number) {
    return this.http.get('userapi/get-closest-quests/' + lat + '/' + lng + '/' + range).map((response: Response) => {
      return response.json();
    }).catch((response: Response) => {
      console.log(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any[]) => {
      this.quests = obj;
      for (let quest of this.quests) {
        quest.uploadDate = new Date(quest.uploadDate);
      }
      this.quests.sort((a, b) => {
        return this.howManyUserPhotosInQuest(b) -
          this.howManyUserPhotosInQuest(a);
      });
    });
  }

  getLoadedQuests(): QuestDTO[] {
    return this.quests;
  }

  getLoadedConfirmations(): SpotConfirmationDTO[] {
    return this.confirmations;
  }

  removeLoadedConfirmations(){
    this.confirmations=null;
  }

  getUserProgressForCurrentUser() {
    return this.authHttp.get('userapi/get-progresses-by-user/').map((response: Response) => {
      return response.json();
    });
  }

  getQuestsByOwner() {
    return this.authHttp.get('userapi/get-quests-by-owner/').map((response: Response) => {
      return response.json();
    });
  }

  getUserProgressByQuest(questId: number) {
    return this.authHttp.get('userapi/get-progress-for-quest/' + questId).map((response: Response) => {
      return response.json();
    });
  }

  getQuestById(id: number) {
    return this.http.get('userapi/get-quest-by-id/' + id).map((response: Response) => {
      return response.json();
    });
  }

  howManyUserPhotosInQuest(questDTO: QuestDTO): number {
    let res = 0;
    for (let spot of questDTO.spots) {


      res += this.howManyUserPhotosInSpot(spot);
    }
    return res;
  }

  howManyUserPhotosInSpot(spotDTO: SpotDTO): number {
    return spotDTO.photos.length - 1;
  }

  howManySpotsInQuest(quest: QuestDTO): number {
    return quest.spots == null ? 0 : quest.spots.length;
  }

  howManySpotsCompleted(progress: UserProgressDTO): number {
    let res = 0;
    for (let spotProgress of progress.userSpotProgresses) {
      if (spotProgress.dateComplete != null)
        res++;
    }
    return res;
  }

  getUserProgressInPercents(progress: UserProgressDTO): number {
    return Math.round((this.howManySpotsCompleted(progress) / this.howManySpotsInQuest(progress.quest)) * 100);
  }

  isSpotCompleted(spot: SpotDTO, userProgress: UserProgressDTO): boolean {
    for (let spotProgress of userProgress.userSpotProgresses) {
      if (spotProgress.spotId == spot.spotId && spotProgress.dateComplete != null)
        return true;
    }
    return false;
  }

  isSpotConfirmed(spot: SpotDTO, userProgress: UserProgressDTO): boolean {
    for (let spotProgress of userProgress.userSpotProgresses) {
      if (spotProgress.spotId == spot.spotId && spotProgress.spotStatus == 'Confirmed')
        return true;
    }
    return false;
  }

  joinQuest(questId: number) {
    return this.authHttp.post('userapi/join-quest/' + questId, {}).map((response: Response) => {
      return response;
    });
  }

  postSpotPhoto(url: string, questId: number, spotId: number) {
    return this.authHttp.post('userapi/post-spot-photo/', {url: url, questId: questId, spotId: spotId}).map((response: Response) => {
      return response;
    });
  }

  getConfirmationsList() {
    return this.authHttp.get('userapi/get-all-confirmations/', {}).map((response: Response) => {
      return response.json();
    }).subscribe(
      (confirmations: any[]) => {
        this.confirmations = confirmations;
        this._confirmationsCountSubject.next(this.confirmations.length);
        for (let confirmation of this.confirmations) {
          confirmation.uploadDate = new Date(confirmation.uploadDate);
        }
        this.confirmations.sort((a, b) => {
          if (a.uploadDate > b.uploadDate)
            return -1;
          else
            return 1;
        });
      });
  }

  confirmationRequest(userProgressId: number, confirm: Boolean) {
    return this.authHttp.post('userapi/confirmation-request/' + userProgressId, {confirm: confirm}).map((response: Response) => {
      return response;
    });
  }
}
