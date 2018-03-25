import {Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef, Directive} from '@angular/core';
import {marker} from '../map/map.component';
import {QuestDTO} from './quest.model';
import {QuestService} from './quest.service';
import {HttpClient, HttpResponse, HttpEventType} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SpotDTO} from "./spot.model";
import {LoaderService} from "./loader.service";
//import {InputFormComponent} from "./input-form/input-form.component";


const URL = 'http://localhost:8081/userapi/upload-photo/';
declare const google: any;

const redMarker = 'https://maps.google.com/mapfiles/ms/icons/red-dot.png';
const greenMarker = 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'

@Component({
  selector: 'app-quest',
  templateUrl: './quest.component.html',
  styleUrls: ['./quest.component.css'],
  providers: [QuestService],
})
export class QuestComponent implements OnInit {


  map:any;
  forms: Array<number> = [];
  errorMsg: string;
  quest: QuestDTO = new QuestDTO('', '', null, 0, '', '');
  photos: FileList;
  questPhoto: File;
  progress: { percentage: number } = {percentage: 0};

  questPlace: marker = {
    lat: 51.690, //inital post (might be initialized being based on browser geoposition)
    lng: 7.890,
    label: '^',
    iconUrl: greenMarker,
    description: null,
    draggable: true,
    quests:[]
  };

    markers:marker[] = [];
    spots:SpotDTO[] = [];

  //@ViewChild('parent', {read : ViewContainerRef}) container: ViewContainerRef;
  //@ViewChild(InputFormComponent) inputForm: InputFormComponent;


  constructor(private questService: QuestService, private loader:LoaderService) {
  }

  ngOnInit() {
  }

  addMarker(){
    //console.log(this.inputForm.quest.name);
  }

  activateMarker(i){
    for(var j=0; j< this.spots.length; j++){
      this.markers[j].label = '✖';
      this.markers[j].draggable = false;
      this.markers[j].iconUrl = redMarker;
    }
    this.markers[i].label = 'O';
    this.markers[i].draggable = true;
    this.markers[i].iconUrl = greenMarker;
  }
  addSpotForm(){
    for(var i=0; i< this.spots.length; i++){
      this.markers[i].label = '✖';
      this.markers[i].draggable = false;
      this.markers[i].iconUrl = redMarker;
    }
    this.spots.push(new SpotDTO('',this.map.getCenter().lat(),this.map.getCenter().lng()));
    var newMarker: marker = {
      lat: this.map.getCenter().lat(), //inital post (might be initialized being based on browser geoposition)
      lng: this.map.getCenter().lng(),
      label: 'O',
      iconUrl: greenMarker,
      description: null,
      draggable: true,
      quests:null,
    };
    this.markers.push(newMarker)
  }
  /*addForm(){

    var comp = this.cfr.resolveComponentFactory(InputFormComponent);
    var expComponent = this.container.createComponent(comp);
    expComponent.instance._ref = expComponent;
    for(var i=0; i< this.spots.length; i++){
      this.spots[i].label = '✖';
      this.spots[i].draggable = false;
      this.spots[i].iconUrl = redMarker;
    }
    var newMarker: marker = {
      lat: this.map.getCenter().lat(), //inital post (might be initialized being based on browser geoposition)
      lng: this.map.getCenter().lng(),
      label: 'O',
      iconUrl: greenMarker,
      description: null,
      draggable: true
    };
    this.spots.push(newMarker);
  }*/

  getPos($event, i) {
    this.markers[i].lng = $event.coords.lng;
    this.markers[i].lat = $event.coords.lat;
    console.log(this.questPlace.lng, "   ", this.questPlace.lat);
  }

  upload() {
    console.log(this.quest.name + ' ,' + this.quest.description + ' ,' + this.quest.reward);
    this.quest.lat = this.questPlace.lat.toString();
    this.quest.lng = this.questPlace.lng.toString();
    this.quest.uploadDate = new Date();


    this.progress.percentage = 0;
    this.questPhoto = this.photos.item(0);

    this.questService.postPhoto(this.questPhoto, this.quest).catch((response: Response) => {
      this.writeMsg(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe((data) => {
      this.writeMsg(data);
      //this.showLink();
    });
    this.photos = undefined;
    this.questPhoto = undefined;
    this.quest = new QuestDTO('', '', null, 0, '', '');
  }

  selectFile(event) {
    this.photos = event.target.files;
    this.questPhoto = this.photos[0];
  }

  writeMsg(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

  createQuest() {
    console.log(this.quest.name + ' ,' + this.quest.description + ' ,' + this.quest.reward);
    this.quest.lat = this.questPlace.lat.toString();
    this.quest.lng = this.questPlace.lng.toString();
    this.quest.uploadDate = new Date();
    this.questService.postQuestInfo(this.quest).catch((response: Response) => {
      this.writeMsg(response.text());
      return Observable.throw(response)
    }).subscribe((data) => {
      this.writeMsg(data);
    });
    this.quest = new QuestDTO('', '', null, 0, '', '');
  }

  mapReady(event){
    this.map = event;
    this.map.controls[google.maps.ControlPosition.TOP_RIGHT].push(document.getElementById("newMarker"));
  }

}
