import { connect } from 'react-redux';
import axios from 'axios';

import WorkspaceForm from '../components/WorkspaceForm';
import { addWorkspace } from '../actions/workspaceActions';
import { notify } from '../actions/notificationActions';

export const postWorkspaceCall = (dispatch, workspace) => {
  axios.post('/api/workspaces/', workspace).then(response => {
    dispatch(addWorkspace(response.data));
    dispatch(notify("Workspace added"));
  });
}

const mapStateToProps = state => ({
  workspace: {name: ""}
})

const mapDispatchToProps = dispatch => ({
  saveWorkspaceHandler: (workspace) => postWorkspaceCall(workspace)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WorkspaceForm)
