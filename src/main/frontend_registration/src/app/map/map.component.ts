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
    'assets/markers/blue.png',
    'assets/markers/lightblue.png',
    'assets/markers/salad.png',
    'assets/markers/green.png',
    'assets/markers/yellowgreen.png',
    'assets/markers/yellow.png',
    'assets/markers/orangeyellow.png',
    'assets/markers/orange.png',
    'assets/markers/red.png'
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
