import React, { Component } from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';

import './Ideas.css';

class Ideas extends Component {

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(prevProps.workspaceId !== this.props.workspaceId) {
      this.props.loadHandler(this.props.workspaceId);
    }
  }

  render() {
    const listItems = this.props.ideas.map((idea) =>
    <ListItem button selected={this.props.selectedIndex === idea.id} key={idea.id}
      onClick={event => this.props.clickHandler(idea.id)}>
      <Avatar className={'rating-' + idea.rating}>
      </Avatar>
      <ListItemText primary={idea.name} secondary={idea.subTitle} />
    </ListItem>);

    return (
      <div>
        <List>
          {listItems}
        </List>
      </div>
    );
  }
}

export default Ideas;
