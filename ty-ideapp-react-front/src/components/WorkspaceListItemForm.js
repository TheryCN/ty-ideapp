import React, { Component } from 'react';

import ListItem from '@material-ui/core/ListItem';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import TextField from '@material-ui/core/TextField';
import IconButton from '@material-ui/core/IconButton';
import SaveIcon from '@material-ui/icons/Save';

class WorkspaceListItemForm extends Component {

  constructor(props) {
    super(props);
    this.state = {workspace: this.props.workspace};
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(prevProps.workspace.id !== this.props.workspace.id) {
      this.setState({workspace: this.props.workspace})
    }
  }

  handleChange = name => event => {
    this.setState({workspace: {...this.state.workspace, [name]: event.target.value}})
  };

  render() {
    const workspace = this.state.workspace;
    return (
      <ListItem button>
        <TextField
          onChange={this.handleChange('name')}
          value={workspace.name}
          margin="normal"
        />
        <ListItemSecondaryAction>
          <IconButton aria-label="Edit Workspace" onClick={() => this.props.saveHandler(workspace)}>
            <SaveIcon />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    );
  }
}

export default WorkspaceListItemForm;
