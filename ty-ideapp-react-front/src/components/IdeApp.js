import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';

import Header from './Header.js';
import LeftLayout from './LeftLayout.js';
import MidLayout from './MidLayout.js';
import Notification from '../containers/Notification.js';
import WorkspaceEditableList from '../containers/WorkspaceEditableList.js';

const menus = [{
	id: 1,
	name: 'Ideas',
	location: '/ideas'
}, {
	id: 2,
	name: 'Workspaces',
	location: '/workspaces'
}];

class IdeApp extends Component {

  constructor(props) {
    super(props);
    let selectedMenus = menus.filter(menu => menu.location === this.props.history.location.pathname);
    let activeMenu = selectedMenus.length > 0 ? selectedMenus[0] : menus[0];
    this.props.history.push(activeMenu.location);
    this.state = { activeMenuId: activeMenu.id };
  }

  componentDidMount() {
    this.props.loadLoggedUserHandler();
  }

  changeActiveMenuHandler = (id) => {
    let selectedMenus = menus.filter(menu => menu.id === id);
    if(selectedMenus.length > 0) {
      this.props.history.push(menus.filter(menu => menu.id === id)[0].location);
    }
    this.setState({ activeMenuId: id });
  }

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
          <Header menus={menus} activeMenuId={this.state.activeMenuId} changeActiveMenuHandler={this.changeActiveMenuHandler} />
          <Grid container className="layout">
            {layout}
          </Grid>
        </div>
      </div>
    );
  }
}

export default IdeApp;
