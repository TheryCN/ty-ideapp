import React, { Component } from 'react';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import Grid from '@material-ui/core/Grid';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';

import Header from './components/Header.js';
import LeftLayout from './components/LeftLayout.js';
import MidLayout from './components/MidLayout.js';
import Notification from './containers/Notification.js';

class App extends Component {
  render() {

    return (
      <MuiThemeProvider theme={theme}>
        <Notification />
        <div className="App">
          <Header />
          <Grid container className="layout">
            <Grid item xs={3} sm={3} className="left-layout">
                <LeftLayout />
            </Grid>
            <Grid item xs={9} sm={9} className="mid-layout">
                <MidLayout />
            </Grid>
          </Grid>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
