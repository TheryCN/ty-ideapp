import React, { Component } from 'react';
import List from '@material-ui/core/List';

import WorkspaceListItemForm from './WorkspaceListItemForm';
import WorkspaceListItemView from './WorkspaceListItemView';

const emptyWorkspace = { id: -1, name: "" };

class WorkspaceEditableList extends Component {

  constructor(props) {
    super(props);
    this.state = { editId: undefined };
    this.editHandler = this.editHandler.bind(this);
  }
  
  componentDidMount() {
    if(this.props.workspaces.length === 0) {
      this.props.loadHandler();
    }
  }

  editHandler(workspace) {
    this.props.editWorkspaceHandler(workspace);
    this.setState({ editId: undefined });
  }

  render() {
    let listItems = [];
    listItems.push(
      <WorkspaceListItemForm key={emptyWorkspace.id} saveHandler={this.props.saveWorkspaceHandler} workspace={emptyWorkspace} />
    );

    if(this.props.workspaces) {
      listItems.push(this.props.workspaces.map((workspace) => {
        if(this.state.editId === workspace.id) {
          return <WorkspaceListItemForm key={workspace.id} saveHandler={this.editHandler} workspace={workspace} />
        } else {
          return <WorkspaceListItemView key={workspace.id} deleteHandler={this.props.deleteWorkspaceHandler} editHandler={(id) => this.setState({ editId: id })} workspace={workspace} />
        }
      }));
    }

    return (
      <div>
        <List>
          {listItems}
        </List>
      </div>
    );
  }
}

export default WorkspaceEditableList;
