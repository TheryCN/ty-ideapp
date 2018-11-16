import React, { Component } from 'react';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';

import Header from './components/Header.js';
import Workspaces from './components/Workspaces.js';

class App extends Component {
  render() {

    return (
      <MuiThemeProvider theme={theme}>
        <div className="App">
          <div>
            <Header />
          </div>
          <div>
            <Workspaces />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
