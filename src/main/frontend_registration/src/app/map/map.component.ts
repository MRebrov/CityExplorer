import {Component, OnInit} from '@angular/core';
import {QuestDTO} from '../quest/quest.model';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {QuestService} from '../quest/quest.service';
import {forEach} from '@angular/router/src/utils/collection';
import {PhotoDTO} from '../quest/photo.model';
import {SpotDTO} from '../quest/spot.model';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  lat: number = 51.6495355;
  lng: number = 39.150856499999996;

  quests: QuestDTO[] = [];

  markers: marker[] = [];

  icons: string[] =[
    'https://psv4.userapi.com/c834500/u32014097/docs/d11/b50e2136de86/blue.png',
    'https://psv4.userapi.com/c834600/u32014097/docs/d1/ca527083e24e/lightblue.png',
    'https://psv4.userapi.com/c834703/u32014097/docs/d11/e2db8a4ccbc9/salad.png',
    'https://psv4.userapi.com/c834502/u32014097/docs/d15/d6fc8007c6ae/green.png',
    'https://psv4.userapi.com/c834502/u32014097/docs/d18/a0b5ac71c7ec/yellowgreen.png',
    'https://psv4.userapi.com/c834600/u32014097/docs/d11/cf82d9ddb7bc/yellow.png',
    'https://psv4.userapi.com/c834604/u32014097/docs/d16/d5a486a47eeb/orangeyellow.png',
    'https://psv4.userapi.com/c834703/u32014097/docs/d18/69c90189eb84/orange.png',
    'https://psv4.userapi.com/c834704/u32014097/docs/d6/a158f001a3de/red.png'
    ];

  loaded: boolean = false;

  constructor(public questService: QuestService) {
  }

  ngOnInit() {
    this.loadLocation();

  }

  loadLocation() {
    if (window.navigator && window.navigator.geolocation) {
      let options = {
        enableHighAccuracy: true,
        timeout: 10000
      };
      window.navigator.geolocation.getCurrentPosition(
        position => {
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
          console.log('Position loaded: ' + position.coords.latitude + ' ' + position.coords.longitude);
          this.loadQuests();
        },
        error => {
          switch (error.code) {
            case 1:
              console.log('Permission Denied');
              alert('We strongly recommend you to allow location detection or you will not be able to see quests that are available near you. Once you did, please reload page.');
              this.loadQuests();
              break;
            case 2:
              console.log('Position Unavailable');
              this.loadQuests();
              break;
            case 3:
              console.log('Timeout');
              this.loadQuests();
              break;
          }
        },
        options
      );
    }
    else {
      console.log('No navigator or geoposition object found');
      this.loadQuests();
    }
  }

  loadQuests() {
    this.questService.getQuestsInRange(this.lat, this.lng, 15000).catch((response: Response) => {
      console.log(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any[]) => {
      this.quests = obj;
      for (let quest of this.quests) {
        quest.uploadDate = new Date(quest.uploadDate);
      }
      this.quests.sort((a, b) => {
        return this.questService.howManyUserPhotosInQuest(b) -
          this.questService.howManyUserPhotosInQuest(a);
      });
      this.loaded = true;
      console.log('Quests for current position loaded successfully');
      this.updateMarkers();
    });
  }

  updateMarkers() {
    this.markers = [];
    for (let quest of this.quests) {
      for (let spot of quest.spots) {
        let existingMarker = this.markers.find(m => this.distFrom(
          m.lat,
          m.lng,
          parseFloat(spot.lat),
          parseFloat(spot.lng)
        ) < 20);
        if (existingMarker == null) {
          this.markers.push({
            lat: parseFloat(spot.lat),
            lng: parseFloat(spot.lng),
            label: null,
            name: spot.name,
            iconUrl: null,
            //description: 'Database does not still support description for spots',
            description: '',
            draggable: false,
            quests: [quest],
            photos: spot.photos
          });
        }
        else {
          existingMarker.quests.push(quest);
          existingMarker.photos = existingMarker.photos.concat(spot.photos);
        }
      }
    }
    this.evaluateMarkers();

  }

  evaluateMarkers() {
    if (this.markers.length > 0) {
      this.markers.sort((a, b) => {
        return a.photos.length - b.photos.length;
      });
      let min = this.markers[0].photos.length;
      let max = this.markers[this.markers.length - 1].photos.length;
      for (let m of this.markers) {
        m.iconUrl = this.icons[Math.round((this.icons.length-1)*((m.photos.length - min) / (max - min)))];
        m.label=m.photos.length.toString();
      }
    }
  }

  distFrom(lat1: number, lng1: number, lat2: number, lng2: number): number {
    let earthRadius = 6371000; //meters
    let dLat = this.radians(lat2 - lat1);
    let dLng = this.radians(lng2 - lng1);
    let a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.radians(lat1)) * Math.cos(this.radians(lat2)) *
      Math.sin(dLng / 2) * Math.sin(dLng / 2);
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    let dist = (earthRadius * c);

    return dist;
  }

  radians(degrees: number): number {
    return degrees * Math.PI / 180;
  };


}

export interface marker {
  lat: number;
  lng: number;
  label?: string;
  name: string;
  iconUrl: string;
  description?: string;
  draggable: boolean;
  photos: PhotoDTO[];
  quests: QuestDTO[];
}
