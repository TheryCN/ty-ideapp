import { connect } from 'react-redux';
import axios from 'axios';
import { fetchWorkspaces, selectWorkspace } from '../actions/workspaceActions';
import Workspaces from '../components/Workspaces';

const fetchWorkspacesCall = (dispatch) => {
  axios.get('/api/workspace/').then(response => {
    dispatch(fetchWorkspaces(response.data));
  });
}

const mapStateToProps = state => ({
  workspaces: state.workspaces.workspaces,
  selectedIndex: state.workspaces.selectedIndex
})

const mapDispatchToProps = dispatch => ({
  loadHandler: workspaceId => fetchWorkspacesCall(dispatch),
  changeHandler: index => dispatch(selectWorkspace(index))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Workspaces)
