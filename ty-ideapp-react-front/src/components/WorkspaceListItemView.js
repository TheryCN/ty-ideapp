import React from 'react';

import ListItem from '@material-ui/core/ListItem';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import IconButton from '@material-ui/core/IconButton';
import EditIcon from '@material-ui/icons/Edit';

const WorkspaceListItemView = ({ workspace, editHandler }) => (
  <ListItem button key={workspace.id}>
    <ListItemText primary={workspace.name} />
    <ListItemSecondaryAction>
      <IconButton aria-label="Edit Workspace" onClick={() => editHandler(workspace.id)}>
        <EditIcon />
      </IconButton>
    </ListItemSecondaryAction>
  </ListItem>
)

export default WorkspaceListItemView;
