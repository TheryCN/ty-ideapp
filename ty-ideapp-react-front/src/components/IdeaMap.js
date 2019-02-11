import React, { Component } from 'react';

import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { Draw, Modify, Snap, Select } from 'ol/interaction.js';
import { Tile as TileLayer, Vector as VectorLayer } from 'ol/layer.js';
import { Vector as VectorSource } from 'ol/source.js';
import XYZ from 'ol/source/XYZ';
import { Fill, Stroke, Style } from 'ol/style.js';
import Feature from 'ol/Feature.js';
import Circle from 'ol/geom/Circle.js';

const mapStyle = {
    margin: 10
};

class IdeaMap extends Component {

  constructor(props) {
    super(props);
    this.selectHandler = this.selectHandler.bind(this);
  }

  componentDidMount() {
    // TODO
    // ol.source.ServerVector
    // Store as GeoJSON -> Postgis
    // Ideas groupment by geographical zone
    var source = new VectorSource();
    var vector = new VectorLayer({
      source: source,
      style: new Style({
        fill: new Fill({
          color: 'rgba(255, 255, 255, 0.2)'
        }),
        stroke: new Stroke({
          color: '#ffcc33',
          width: 2
        })
      })
    });

    source.addFeature(new Feature(new Circle([258296.24484698195, 5815573.833591854, 923926.3105553072, 5815573.833591854], 665630.0657083252)));

    var map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new XYZ({
            url: 'https://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png'
          })
        }),
        vector
      ],
      view: new View({
        center: [0, 0],
        zoom: 2
      })
    });

    var modify = new Modify({source: source});
    map.addInteraction(modify);

    var draw, snap, select;
    draw = new Draw({
      source: source,
      type: 'Circle'
    });
    map.addInteraction(draw);
    select = new Select();
    map.addInteraction(select);
    select.setActive(false);

    snap = new Snap({source: source});
    map.addInteraction(snap);

    var self = this;
    document.addEventListener('keydown', function(evt) {
        if(evt.keyCode === 46) {
          select.getFeatures().forEach(function(feature) {
            source.removeFeature(feature);
          });
          self.clearSelection(select);
        }
    });

    var extent = source.getExtent();
    map.getView().fit(extent, map.getSize());
    this.setState({ map: map, select: select, draw: draw });
  }

  selectHandler() {
    if(!this.state.select.getActive()) {
      this.state.select.setActive(true);
      this.state.draw.setActive(false);
    } else {
      this.clearSelection(this.state.select);
      this.state.select.setActive(false);
      this.state.draw.setActive(true);
    }
  }

  clearSelection(select) {
    if(select) {
      var selectedFeatures = select.getFeatures();
      selectedFeatures.forEach(function(feature) {
        selectedFeatures.remove(feature);
      });
    }
  }

  render() {
    return (
      <div>
        <div id="map" style={mapStyle}></div>
        <div onClick={this.selectHandler}>Select</div>
      </div>
    );
  }
}

export default IdeaMap;
