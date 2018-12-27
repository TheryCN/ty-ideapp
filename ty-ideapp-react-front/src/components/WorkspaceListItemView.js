import React from 'react';

import ListItem from '@material-ui/core/ListItem';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import IconButton from '@material-ui/core/IconButton';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';

const WorkspaceListItemView = ({ workspace, editHandler, deleteHandler }) => (
  <ListItem button key={workspace.id}>
    <ListItemText primary={workspace.name} />
    <ListItemSecondaryAction>
      <IconButton aria-label="Edit Workspace" onClick={() => editHandler(workspace.id)}>
        <EditIcon />
      </IconButton>
      <IconButton aria-label="Delete Workspace" onClick={() => deleteHandler(workspace.id)}>
        <DeleteIcon />
      </IconButton>
    </ListItemSecondaryAction>
  </ListItem>
)

export default WorkspaceListItemView;
