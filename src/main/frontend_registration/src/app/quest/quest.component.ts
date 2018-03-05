import { Component, OnInit } from '@angular/core';
import {marker} from '../map/map.component';
import {Quest} from "./quest.model";
import {QuestService} from "./quest.service";
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
@Component({
  selector: 'app-quest',
  templateUrl: './quest.component.html',
  styleUrls: ['./quest.component.css'],
  providers:[QuestService]
})
export class QuestComponent implements OnInit {

  errorMsg:string;
  quest:Quest;
  photos:FileList;
  questPhoto:File;
  progress: { percentage: number } = { percentage: 0 }

  questPlace:marker = {
    lat: 51.690, //inital post (might be initialized being based on browser geoposition)
    lng: 7.890,
    label: 'Q',
    iconUrl: null,
    description: null,
  };


  constructor(private questService:QuestService) { }

  ngOnInit() {
  }

  getPos($event){
    this.questPlace.lng = $event.coords.lng;
    this.questPlace.lat = $event.coords.lat;
    //console.log(event.latLng.lng(), "   ", event.latLng.lat() );
  }

  upload(){
    this.progress.percentage = 0;
    this.questPhoto = this.photos.item(0);
    this.questService.loadFile(this.questPhoto).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    this.photos = undefined
  }

  selectFile(event){
    this.photos = event.target.files;
  }

}
