import React, { Component } from 'react';
import './App.css';
import logo from './assets/ideapp.PNG';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import Workspaces from './components/Workspaces.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <img src={logo} width="300" alt="IdeApp"/>
        <MuiThemeProvider>
          <Workspaces />
        </MuiThemeProvider>
      </div>
    );
  }
}

export default App;
