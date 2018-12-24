import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

class WorkspaceForm extends Component {

  constructor(props) {
    super(props);
    this.workspace = {workspace: this.props.workspace};
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
    return (
      <div>
        <div>
          <TextField
            label="Name"
            onChange={this.handleChange('name')}
            value={this.state.workspace.name}
          />
        </div>
        <div>
          <Button variant="contained" onClick={() => this.props.saveWorkspaceHandler(this.props.workspaceId)}>
            Sauvegarder
          </Button>
        </div>
      </div>
    );
  }
}

export default WorkspaceForm;
