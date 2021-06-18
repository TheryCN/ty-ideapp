import React, { Component } from 'react';

import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { Draw, Modify, Snap, Select } from 'ol/interaction.js';
import { Tile as TileLayer, Vector as VectorLayer } from 'ol/layer.js';
import { Vector as VectorSource } from 'ol/source.js';
import OSM from 'ol/source/OSM';
import { Fill, Stroke, Style } from 'ol/style.js';
import Feature from 'ol/Feature.js';
import Circle from 'ol/geom/Circle.js';

const mapStyle = {
    marginTop: 10,
    marginBottom: 10,
    width: "100%",
    height: 300
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
    var self = this;
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

    var map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM()
        }),
        vector
      ],
      view: new View({
        center: [0, 0],
        zoom: 2
      })
    });

    var select, draw;

    if(!this.props.readOnly) {
      var modify = new Modify({source: source});
      map.addInteraction(modify);

      var snap;
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

      document.addEventListener('keydown', function(evt) {
          if(evt.keyCode === 46) {
            select.getFeatures().forEach(function(feature) {
              source.removeFeature(feature);
            });
            self.clearSelection(select);
          }
      });

      vector.on('change', function(e) {
        self.props.onChange(vector.getSource().getFeatures());
      });
    }


    this.initVectorLayer(map);
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

  initVectorLayer(map) {
    let vector = map.getLayers().getArray()[1];
    let source = vector.getSource();
    source.clear();

    if(this.props.localizations && this.props.localizations.length > 0) {
      this.props.localizations.forEach(function(localization) {
        source.addFeature(new Feature(new Circle(localization.coordinates, localization.radius)));
      });
      var extent = source.getExtent();
      map.getView().fit(extent, map.getSize());
    }
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(JSON.stringify(prevProps.localizations) !== JSON.stringify(this.props.localizations)) {
      this.initVectorLayer(this.state.map);
    }
  }

  render() {
    if(this.props.readOnly) {
      let customMapStyle = {...mapStyle};
      if(this.props.localizations.length === 0){
        customMapStyle = {...customMapStyle, display: "none"}
      }
      return (
        <div>
          <div id="map" style={customMapStyle}></div>
        </div>
      );
    } else {
      return (
        <div>
          <div id="map" style={mapStyle}></div>
          <div onClick={this.selectHandler}>Select</div>
        </div>
      );
    }

  }
}

export default IdeaMap;
