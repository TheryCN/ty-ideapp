import { connect } from 'react-redux';
import axios from 'axios';

import WorkspaceEditableList from '../components/WorkspaceEditableList';
import { addWorkspace, editWorkspace, deleteWorkspace } from '../actions/workspaceActions';
import { notify } from '../actions/notificationActions';

export const postWorkspaceAddCall = (dispatch, workspace) => {
  axios.post('/api/workspaces/', workspace).then(response => {
    dispatch(addWorkspace(response.data));
    dispatch(notify("Workspace added"));
  });
}

export const postWorkspaceEditCall = (dispatch, workspace) => {
  axios.post('/api/workspaces/', workspace).then(response => {
    dispatch(editWorkspace(response.data));
    dispatch(notify("Workspace edited"));
  });
}
export const deleteWorkspaceCall = (dispatch, workspaceId) => {
  axios.delete('/api/workspaces/'+workspaceId).then(response => {
    dispatch(deleteWorkspace(workspaceId));
    dispatch(notify("Workspace deleted"));
  });
}

const mapStateToProps = state => ({
  workspaces: state.workspaces.workspaces
})

const mapDispatchToProps = dispatch => ({
  saveWorkspaceHandler: (workspace) => postWorkspaceAddCall(dispatch, workspace),
  editWorkspaceHandler: (workspace) => postWorkspaceEditCall(dispatch, workspace),
  deleteWorkspaceHandler: (workspaceId) => deleteWorkspaceCall(dispatch, workspaceId)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WorkspaceEditableList)