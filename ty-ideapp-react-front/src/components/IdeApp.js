import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';

import Header from './Header.js';
import LeftLayout from './LeftLayout.js';
import MidLayout from './MidLayout.js';
import Notification from '../containers/Notification.js';
import WorkspaceEditableList from '../containers/WorkspaceEditableList.js';

class IdeApp extends Component {

  constructor(props) {
    super(props);
    this.state = { activeMenuId: 1 };
  }

  componentDidMount() {
    this.props.loadLoggedUserHandler();
  }

  changeActiveMenuHandler = (id) => this.setState({ activeMenuId: id })

  render() {
    if(!this.props.loggedUserLoaded) {
      return <div />;
    } else if(this.props.loggedUser === undefined) {
      return <Redirect to='/login' />
    }

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
      <div>
        <Notification />
        <div className="App">
          <Header activeMenuId={this.state.activeMenuId} changeActiveMenuHandler={this.changeActiveMenuHandler} />
          <Grid container className="layout">
            {layout}
          </Grid>
        </div>
      </div>
    );
  }
}

export default IdeApp;
