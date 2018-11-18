import React, { Component } from 'react';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import Grid from '@material-ui/core/Grid';
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
          <Grid container>
            <Grid item xs={12} sm={12}>
                <Header />
            </Grid>
            <Grid item xs={12} sm={12}>
                <Workspaces />
            </Grid>
          </Grid>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
