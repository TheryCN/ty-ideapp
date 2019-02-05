import React, { Component } from 'react';

import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';

import IdeApp from './containers/IdeApp.js';
import Login from './containers/Login.js';

class App extends Component {

  render() {
    return (
      <MuiThemeProvider theme={theme}>
        <Router>
          <Switch>
            <Route path="/login" component={Login} />
            <Route path="/" component={IdeApp} />
          </Switch>
        </Router>
      </MuiThemeProvider>
    );
  }
}

export default App;
