import React, { Component } from 'react';
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import Grid from '@material-ui/core/Grid';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';
import axios from 'axios';

import Header from './components/Header.js';
import LeftLayout from './components/LeftLayout.js';
import MidLayout from './components/MidLayout.js';
import Notification from './containers/Notification.js';
import WorkspaceEditableList from './containers/WorkspaceEditableList.js';

class App extends Component {

  constructor(props) {
    super(props);
    axios.defaults.headers.common['Authorization'] = 'Basic VGhlcnk6Q2hhbmdlSXQ=';
    this.state = { activeMenuId: 1 };
  }

  changeActiveMenuHandler = (id) => this.setState({ activeMenuId: id })

  render() {
    let layout = [];
    if(this.state.activeMenuId === 1) {
      layout.push(
        <Grid key={1} item xs={3} sm={3} className="left-layout">
            <LeftLayout />
        </Grid>);
      layout.push(
        <Grid key={2} item xs={9} sm={9} className="mid-layout">
            <MidLayout />
        </Grid>);
    } else {
      layout.push(
        <Grid key={1} item xs={12} sm={12} className="left-layout">
          <WorkspaceEditableList />
        </Grid>
      );
    }

    return (
      <MuiThemeProvider theme={theme}>
        <Notification />
        <div className="App">
          <Header activeMenuId={this.state.activeMenuId} changeActiveMenuHandler={this.changeActiveMenuHandler} />
          <Grid container className="layout">
            {layout}
          </Grid>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
