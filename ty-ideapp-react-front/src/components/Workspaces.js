import React, { Component } from 'react';
import axios from 'axios';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Ideas from '../containers/Ideas.js';
import Idea from '../containers/Idea.js';

class Workspaces extends Component {
  constructor(props) {
    super(props);
    this.state = {workspaces: [], value: 0};
  }

  componentDidMount() {
    axios.get('/api/workspaces').then(response => {
      this.setState({workspaces: response.data._embedded.workspaces, value: response.data._embedded.workspaces[0].id});
    });
  }

  handleChange = (event, index, value) => this.setState({value: event.target.value});

  render() {
    const listItems = this.state.workspaces.map((workspace) => <MenuItem key={workspace.id} value={workspace.id}>{workspace.name}</MenuItem>);

    // Ideas is a subelement of Workspaces only because props are readonly from childs
    return (
      <div>
        <FormControl>
          <InputLabel htmlFor="workspacesId">Workspaces</InputLabel>
          <Select
            value={this.state.value}
            onChange={this.handleChange}
            inputProps={{
              name: 'value',
              id: 'workspacesId'
            }}
          >
            {listItems}
          </Select>

          <Ideas workspaceId={this.state.value} />
          <Idea />
        </FormControl>
      </div>
    );
  }
}

export default Workspaces;
