import {Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef, Directive} from '@angular/core';
import {marker} from '../map/map.component';
import {QuestDTO} from './quest.model';
import {QuestService} from './quest.service';
import {HttpClient, HttpResponse, HttpEventType} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SpotDTO} from "./spot.model";
import {LoaderService} from "./loader.service";
import {PhotoDTO} from "./photo.model";
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";
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


  map: any;
  forms: Array<number> = [];
  errorMsg: string;
  quest: QuestDTO = new QuestDTO('', '', null, 0, 10);
  photos: FileList;
  questPhotos: File[] = [];
  progress: { percentage: number } = {percentage: 0};
  photoAdded: boolean[] = [];
  balance: number;
  cost: number;
  reward: number;
  numberOfParticipants: number;

  questPlace: marker = {
    lat: 51.690, //inital post (might be initialized being based on browser geoposition)
    lng: 7.890,
    label: '^',
    name: null,
    iconUrl: greenMarker,
    description: null,
    draggable: true,
    quests: [],
    photos: []
  };

  markers: marker[] = [];
  spots: SpotDTO[] = [];

  //@ViewChild('parent', {read : ViewContainerRef}) container: ViewContainerRef;
  //@ViewChild(InputFormComponent) inputForm: InputFormComponent;


  constructor(private questService: QuestService, private loader: LoaderService, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(
      (user: any) => {
        this.balance = user.balance;
      });
  }


  calculateCost(){
    var k = this.spots.length;
    var n = 1;
    switch(k) {
      case 2: n = 2;
        break;
      case 3: n = 4;
        break;
      case 4: n = 7;
        break;
      case 5: n = 11;
        break;
    }
    this.cost = this.reward*this.numberOfParticipants*n;
  }


  addMarker() {
    //console.log(this.inputForm.quest.name);
  }

  activateMarker(i) {
    for (var j = 0; j < this.spots.length; j++) {
      this.markers[j].label = '✖';
      this.markers[j].draggable = false;
      this.markers[j].iconUrl = redMarker;
    }
    this.markers[i].label = 'O';
    this.markers[i].draggable = true;
    this.markers[i].iconUrl = greenMarker;
  }

  addSpotForm() {
    for (var i = 0; i < this.spots.length; i++) {
      this.markers[i].label = '✖';
      this.markers[i].draggable = false;
      this.markers[i].iconUrl = redMarker;
      console.log("name-"+i+"-"+this.spots[i].name);
    }
    this.spots.push(new SpotDTO('', this.map.getCenter().lat(), this.map.getCenter().lng()));
    var newMarker: marker = {
      lat: this.map.getCenter().lat(), //inital post (might be initialized being based on browser geoposition)
      lng: this.map.getCenter().lng(),
      label: 'O',
      name: null,
      iconUrl: greenMarker,
      description: null,
      draggable: true,
      quests: null,
      photos: null
    };
    this.markers.push(newMarker);
    this.photoAdded.push(false);
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
    this.spots[i].lng = $event.coords.lng;
    this.spots[i].lat = $event.coords.lat;
    console.log(this.spots[i].lng, "   ", this.spots[i].lat);
  }

  // upload() {
  //   console.log(this.quest.name + ' ,' + this.quest.description + ' ,' + this.quest.reward);
  //   this.quest.lat = this.questPlace.lat.toString();
  //   this.quest.lng = this.questPlace.lng.toString();
  //   this.quest.uploadDate = new Date();
  //
  //
  //   this.progress.percentage = 0;
  //   //this.questPhoto = this.photos.item(0);
  //
  //   this.questService.postPhoto(this.questPhoto, this.quest).catch((response: Response) => {
  //     this.writeMsg(response.text()); //если ошибка, вывести её
  //     return Observable.throw(response);
  //   }).subscribe((data) => {
  //     this.writeMsg(data);
  //     //this.showLink();
  //   });
  //   this.photos = undefined;
  //   //this.questPhoto = undefined;
  //   this.quest = new QuestDTO('', '', null, 0, '', '');
  // }

  selectFile(event, i) {
    this.photos = event.target.files;
    this.questPhotos[i] = this.photos[0];
    this.photoAdded[i] = true;
  }

  writeMsg(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

  createQuest() {
    console.log(this.quest.name + ' ,' + this.quest.description + ' ,' + this.quest.reward + ' ,' + this.quest.numberOfParticipants);
    this.quest.spots = this.spots;
    this.quest.photoURL = this.spots[0].photos[0].url;
    this.quest.uploadDate = new Date();
    this.questService.postQuestInfo(this.quest).catch((response: Response) => {
      this.writeMsg(response.text());
      return Observable.throw(response)
    }).subscribe((data) => {
      this.writeMsg(data);
    });
    this.quest = new QuestDTO('', '', null, 0, 10);
    this.spots = [];
    this.questPhotos = [];
    this.markers = [];
  }

  mapReady(event) {
    this.map = event;
    this.map.controls[google.maps.ControlPosition.TOP_RIGHT].push(document.getElementById("newMarker"));
  }

  uploadSpotPhoto(spotName:string, i) {
    console.log('i: ' + i);
    console.log('spotName: ' + spotName);
    this.spots[i].name = spotName;
    this.spots[i].uploadDate = new Date();
    this.spots[i].photos = [new PhotoDTO('', '')];
    this.spots[i].photos[0].uploadDate = new Date();
    this.spots[i].photos[0].photoType = 'spot';
    console.log(this.spots[i].photos[0].uploadDate);

    this.questService.uploadSpotPhoto(this.questPhotos[i]).catch((response) => {
      return Observable.throw(response)
    }).subscribe((data) => {
        console.log(data);
        this.spots[i].photos[0].url = data;
      },
      (error) => {
        console.log(error)
      });
  }

  deleteForm(i){
    this.spots.splice(i,1);
    this.markers.splice(i,1);
    if(this.questPhotos[i] != null){
      this.questPhotos.splice(i,1);
    }
  }
}
