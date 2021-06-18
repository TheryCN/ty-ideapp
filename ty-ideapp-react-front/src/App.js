import React, { Component } from 'react';

import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import 'typeface-roboto';
import theme from './theme.js';

import IdeApp from './containers/IdeApp.js';
import LoginPage from './components/LoginPage.js';

class App extends Component {

  render() {
    return (
      <MuiThemeProvider theme={theme}>
        <Router>
          <Switch>
            <Route path="/login" component={LoginPage} />
            <Route path="/" component={IdeApp} />
          </Switch>
        </Router>
      </MuiThemeProvider>
    );
  }
}

export default App;
