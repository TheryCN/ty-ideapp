import { connect } from 'react-redux';
import { fetchWorkspacesCall, selectWorkspace } from '../actions/workspaceActions';
import { updateWorkspaceCounter } from '../actions/workspaceActions';

import IdeaWorkspaces from '../components/IdeaWorkspaces';

const mapStateToProps = state => ({
  workspaces: state.workspaces.workspaces,
  selectedIndex: state.workspaces.selectedIndex,
  ideas: state.ideas.ideas,
  loggedUser: state.users.loggedUser
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
