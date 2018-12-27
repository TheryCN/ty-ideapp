import { connect } from 'react-redux';
import axios from 'axios';
import { fetchWorkspaces, selectWorkspace } from '../actions/workspaceActions';
import { updateWorkspaceCounter } from '../actions/workspaceActions';

import IdeaWorkspaces from '../components/IdeaWorkspaces';

const fetchWorkspacesCall = (dispatch) => {
  axios.get('/api/workspace/').then(response => {
    dispatch(fetchWorkspaces(response.data));
  });
}

const mapStateToProps = state => ({
  workspaces: state.workspaces.workspaces,
  selectedIndex: state.workspaces.selectedIndex,
  ideas: state.ideas.ideas
})

const mapDispatchToProps = dispatch => ({
  loadHandler: workspaceId => fetchWorkspacesCall(dispatch),
  changeHandler: index => dispatch(selectWorkspace(index)),
  updateHandler: (index, size) => dispatch(updateWorkspaceCounter(index, size))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeaWorkspaces)
