import React, { Component } from 'react';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import Grid from '@material-ui/core/Grid';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';

import Header from './components/Header.js';
import Workspaces from './containers/Workspaces.js';
import Idea from './containers/Idea.js';
import Notification from './containers/Notification.js';

class App extends Component {
  render() {

    return (
      <MuiThemeProvider theme={theme}>
        <Notification />
        <div className="App">
          <Grid container>
            <Grid item xs={12} sm={12}>
                <Header />
            </Grid>
            <Grid item xs={3} sm={3} className="left-layout">
                <Workspaces />
            </Grid>
            <Grid item xs={9} sm={9} className="mid-layout">
                <Idea />
            </Grid>
          </Grid>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
