import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

import Notification from '../containers/Notification.js';
import Header from './Header.js';
import IdeasLayout from './IdeasLayout.js';
import WorkspaceEditableList from '../containers/WorkspaceEditableList.js';
import ProfileLayout from './ProfileLayout.js';

const menus = [{
	id: 1,
	name: 'Ideas',
	location: '/ideas',
	render: <IdeasLayout />
}, {
	id: 2,
	name: 'Workspaces',
	location: '/workspaces',
	render: <WorkspaceEditableList />
}, {
	id: 3,
	name: 'Profile',
	location: '/profile',
	render: <ProfileLayout />
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

    let layout;
		let selectedMenus = menus.filter(menu => menu.id === this.state.activeMenuId);
    if(selectedMenus.length > 0) {
			layout = selectedMenus[0].render;
		} else {
			layout = <IdeasLayout />;
		}

    return (
      <div>
        <Notification />
        <div className="App">
          <Header menus={menus} activeMenuId={this.state.activeMenuId} changeActiveMenuHandler={this.changeActiveMenuHandler} />
          {layout}
        </div>
      </div>
    );
  }
}

export default IdeApp;
