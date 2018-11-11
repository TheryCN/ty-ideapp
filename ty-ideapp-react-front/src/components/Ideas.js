import React, { Component } from 'react';
import axios from 'axios';
import {List, ListItem} from 'material-ui/List';

class Ideas extends Component {
  constructor(props) {
    super(props);
    this.state = {ideas: []};
  }

  // Wait until props available
  componentWillReceiveProps(nextProps){
    axios.get('/api/workspaces/'+nextProps.workspaceId+'/ideas').then(response => {
      this.setState({ideas: response.data._embedded.ideas});
    });
  }

  render() {
    const listItems = this.state.ideas.map((idea) => <ListItem primaryText={idea.name} secondaryText={idea.description} />);

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
