import React, { Component } from 'react';
import {List, ListItem} from 'material-ui/List';

class Ideas extends Component {

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(prevProps.workspaceId !== this.props.workspaceId) {
      this.props.loadHandler(this.props.workspaceId);
    }
  }

  render() {
    const listItems = this.props.ideas.map((idea) => <ListItem primaryText={idea.name} secondaryText={idea.description} />);

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
