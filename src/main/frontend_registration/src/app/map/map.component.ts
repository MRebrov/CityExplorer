import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  lat: number = 51.678;
  lng: number = 7.891;

  constructor() { }

  ngOnInit() {
  }

  markers: marker[] = [
    {
      lat: 51.673,
      lng: 7.888,
      label: 'Top1',
      iconUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFzPTtaVLS7029u35BpMoJP-7RdfA8GH3mCD50ge12uD2XXTwi',
      description: 'This is top 1 place in rating'
    },
    {
      lat: 51.679,
      lng: 7.990,
      label: 'Top2',
      iconUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFzPTtaVLS7029u35BpMoJP-7RdfA8GH3mCD50ge12uD2XXTwi'
    }
  ]



}

export interface marker {
  lat: number;
  lng: number;
  label?: string;
  iconUrl: string;
  description?:string;
}
