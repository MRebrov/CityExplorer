import {Component, OnInit} from '@angular/core';
import {marker} from '../map/map.component';
import {Quest} from './quest.model';
import {QuestService} from './quest.service';
import {HttpClient, HttpResponse, HttpEventType} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {FileUploader} from "ng2-file-upload";


const URL = 'http://localhost:8081/userapi/upload-photo/';


@Component({
  selector: 'app-quest',
  templateUrl: './quest.component.html',
  styleUrls: ['./quest.component.css'],
  providers: [QuestService]
})
export class QuestComponent implements OnInit {

  errorMsg: string;
  quest: Quest = new Quest('', '', null, 0, '', '');
  photos: FileList;
  questPhoto: File;
  progress: { percentage: number } = {percentage: 0};

  questPlace: marker = {
    lat: 51.690, //inital post (might be initialized being based on browser geoposition)
    lng: 7.890,
    label: 'Q',
    iconUrl: null,
    description: null,
  };


  constructor(private questService: QuestService) {
  }

  ngOnInit() {
  }

  getPos($event) {
    this.questPlace.lng = $event.coords.lng;
    this.questPlace.lat = $event.coords.lat;
    console.log(this.questPlace.lng, "   ",this.questPlace.lat);
  }

  upload() {
    this.progress.percentage = 0;
    this.questPhoto = this.photos.item(0);
    this.questService.postPhoto(this.questPhoto).catch((response: Response) => {
      this.writeMsg(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe((data) => {
      this.writeMsg(data);
      //this.showLink();
    });
    this.photos = undefined;
    this.questPhoto = undefined;
  }

  selectFile(event) {
    this.photos = event.target.files;
    this.questPhoto = this.photos[0];
  }

  writeMsg(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

  createQuest(event) {
    console.log(this.quest.name + ' ,' + this.quest.description + ' ,' + this.quest.reward);
    this.quest.lat = this.questPlace.lat.toString();
    this.quest.lng = this.questPlace.lng.toString();
    this.quest.uploadDate = new Date();
    this.questService.postQuestInfo(this.quest).catch((response: Response) => {
      this.writeMsg(response.text());
      return Observable.throw(response)
    }).subscribe((data) =>{
      this.writeMsg(data);
    });
    this.quest = new Quest('', '', null, 0, '', '');
  }

}
