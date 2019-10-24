import { Component, OnInit } from '@angular/core';


declare var ol: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  latitude: number;
  longitude: number
  map: any;

  constructor() { 

  }

  ngOnInit() {
    (async ()=>{
    await this.findMe();

    this.map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([this.longitude, this.latitude]),
        zoom: 8
      })
    }
    );
    }
    )();
   
    
  }

 setCenter() {
    var view = this.map.getView();
    view.setCenter(ol.proj.fromLonLat([this.longitude, this.latitude]));
    view.setZoom(20);
  }

async findMe() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.setPosition(position);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

setPosition(position) {
    this.latitude = position.coords.latitude;
    this.longitude = position.coords.longitude;
    this.setCenter();

    var source = new ol.source.vector({});
    var layer = new ol.layer.vector({ source: source});
    this.map.addLayer(layer );

    var marker = new ol.Feature({
    geometry: new ol.geom.Point([this.longitude,this.latitude]) // dont worry about coordinate type 0,0 will be in west coast of africa
    }); 

    source.addFeature(marker);
  }

}
