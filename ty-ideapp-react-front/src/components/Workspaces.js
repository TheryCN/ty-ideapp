import React, { Component } from 'react';
import axios from 'axios';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import Ideas from './Ideas.js';

class Workspaces extends Component {
  constructor(props) {
    super(props);
    this.state = {workspaces: []};
  }

  componentDidMount() {
    axios.get('/api/workspaces').then(response => {
      this.setState({workspaces: response.data._embedded.workspaces, value: response.data._embedded.workspaces[0].id});
    });
  }

  handleChange = (event, index, value) => this.setState({value});

  render() {
    const listItems = this.state.workspaces.map((workspace) => <MenuItem value={workspace.id} primaryText={workspace.name} />);

    // Ideas is a subelement of Workspaces only because props are readonly from childs
    return (
      <div>
        <SelectField
          floatingLabelText="Workspaces"
          value={this.state.value}
          onChange={this.handleChange}
          autoWidth={true}
        >
          {listItems}
        </SelectField>

        <Ideas workspaceId={this.state.value} />
      </div>
    );
  }
}

export default Workspaces;
