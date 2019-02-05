import React, { Component } from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';

class IdeaWorkspaces extends Component {

  componentDidMount() {
    if(this.props.workspaces.length === 0) {
      this.props.loadHandler();
    }
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(prevProps.ideas.length !== this.props.ideas.length || prevProps.loggedUser.id !== this.props.loggedUser.id) {
      this.props.updateHandler(this.props.selectedIndex, this.props.ideas.length);
    }
  }

  render() {
    const listItems = this.props.workspaces.map((workspace) =>
    <ListItem button selected={this.props.selectedIndex === workspace.id} key={workspace.id}
      onClick={event => this.props.changeHandler(workspace.id)}>
      <Avatar>
        {workspace.nbIdeas}
      </Avatar>
      <ListItemText primary={workspace.name} />
    </ListItem>);
    // Ideas is a subelement of Workspaces only because props are readonly from childs
    return (
      <div>
          <List>
            {listItems}
          </List>
      </div>
    );
  }
}

export default IdeaWorkspaces;
