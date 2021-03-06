import React from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import UserHeader from '../containers/UserHeader';
import logo from '../assets/ideapp.PNG';
import './Header.css';

const Header = ({ menus=[], activeMenuId = 1, changeActiveMenuHandler }) => {
  const listItems = menus.map(menu =>
    <ListItem classes={(menu.id === activeMenuId) ? { root: 'menu-selected' } : {}}
      selected={menu.id === activeMenuId} key={menu.id} onClick={event => changeActiveMenuHandler(menu.id)}>
      <ListItemText classes={(menu.id === activeMenuId) ? { primary: 'menu-selected' } : {}}
        className="item" primary={menu.name} />
    </ListItem>
  );

  return (
    <div className="header">
      <div className="logo">
        <img src={logo} width="150" alt="IdeApp"/>
      </div>

      <List className="menu">
        {listItems}
      </List>
      <div className="right">
        {process.env.REACT_APP_VERSION} / {process.env.REACT_APP_HOST_ENV}
      </div>
      <div className="right">
        <UserHeader />
      </div>
    </div>
  );
}

export default Header;
