import { connect } from 'react-redux';
import axios from 'axios';

import IdeaAdd from '../components/IdeaAdd';
import { saveIdea } from '../actions/ideaActions';

export const fetchPostIdeaCall = (dispatch, idea, workspaceId) => {
  idea.workspaceId = workspaceId;
  axios.post('/api/idea/', idea).then(response => {
    dispatch(saveIdea(response.data));
  });
}

const mapStateToProps = state => ({
  workspaceId: state.workspaces.selectedIndex
})

const mapDispatchToProps = dispatch => ({
  saveIdeaHandler: (idea, workspaceId) => fetchPostIdeaCall(dispatch, idea, workspaceId)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeaAdd)
