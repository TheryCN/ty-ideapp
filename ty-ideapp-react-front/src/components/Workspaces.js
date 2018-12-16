import React, { Component } from 'react';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Ideas from '../containers/Ideas.js';

class Workspaces extends Component {

  componentDidMount() {
    this.props.loadHandler();
  }

  render() {
    const listItems = this.props.workspaces.map((workspace) => <MenuItem key={workspace.id} value={workspace.id}>{workspace.name}</MenuItem>);

    // Ideas is a subelement of Workspaces only because props are readonly from childs
    return (
      <div>
        <FormControl>
          <InputLabel htmlFor="workspacesId">Workspaces</InputLabel>
          <Select
            value={this.props.selectedIndex}
            onChange={(event) => this.props.changeHandler(event.target.value)}
            inputProps={{
              name: 'value',
              id: 'workspacesId'
            }}
          >
            {listItems}
          </Select>

          <Ideas />
        </FormControl>
      </div>
    );
  }
}

export default Workspaces;
