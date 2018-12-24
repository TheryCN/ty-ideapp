import React from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import logo from '../assets/ideapp.PNG';
import './Header.css';

const menus = [{id: 1, name: "Workspaces"}, {id: 2, name: "Ideas"}];

const Header = ({ activeMenuId = 2 }) => {
  const listItems = menus.map(menu =>
    <ListItem classes={(menu.id === activeMenuId) ? { root: 'menu-selected' } : {}}
      selected={menu.id === activeMenuId} key={menu.id}>
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
    </div>
  );
}

export default Header;
