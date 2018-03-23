import {Component, OnInit} from '@angular/core';
import {QuestDTO} from '../quest/quest.model';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {QuestService} from '../quest/quest.service';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  lat: number = 55.75222;
  lng: number = 37.6155600;

  quests: QuestDTO[];

  markers: marker[] = [];

  constructor(private questService: QuestService) {
  }

  ngOnInit() {
    this.loadLocation();

  }

  loadLocation() {
    if (window.navigator && window.navigator.geolocation) {
      window.navigator.geolocation.getCurrentPosition(
        position => {
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
          console.log(position);
          this.loadQuests();
        },
        error => {
          switch (error.code) {
            case 1:
              console.log('Permission Denied')
              alert('We strongly recommend you to allow location detection or you will not be able to see quests that are available near you. Once you did, please reload page.');
              break;
            case 2:
              console.log('Position Unavailable');
              break;
            case 3:
              console.log('Timeout');
              break;
          }
          this.loadQuests();
        }
      );
    }
    ;
  }

  loadQuests() {
    this.questService.getQuestsInRange(this.lat, this.lng, 15000).catch((response: Response) => {
      console.log(response.text());
      return Observable.throw(response);
    }).subscribe((obj: any[]) => {
      this.quests = obj;
      this.updateMarkers();
      console.log('Quests loaded successfully');
    });
  }

  updateMarkers() {
    this.markers = [];
    for (let quest of this.quests) {
      for (let spot of quest.spots) {
        let existingMarker=this.markers.find(m => this.distFrom(
          m.lat,
          m.lng,
          parseFloat(spot.lat),
          parseFloat(spot.lng)
        ) < 20);
        if (existingMarker == null) {
          this.markers.push({
            lat: parseFloat(spot.lat),
            lng: parseFloat(spot.lng),
            label: spot.name,
            iconUrl: spot.photos[0].url,
            description: 'Database does not still support description for spots',
            draggable: false,
            quests: [quest]
          });
        }
        else{
          existingMarker.quests.push(quest);
        }
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
  iconUrl: string;
  description?: string;
  draggable: boolean;
  quests: QuestDTO[];
}
