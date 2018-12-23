import { connect } from 'react-redux';
import axios from 'axios';

import IdeaForm from '../components/IdeaForm';
import { editIdea } from '../actions/ideaActions';
import { notify } from '../actions/notificationActions';

export const fetchPostIdeaCall = (dispatch, idea, workspaceId) => {
  idea.workspaceId = workspaceId;
  axios.post('/api/idea/', idea).then(response => {
    dispatch(editIdea(response.data));
    dispatch(notify("Idea edited"));
  });
}

const mapStateToProps = state => ({
  workspaceId: state.workspaces.selectedIndex,
  idea: state.ideas.ideas.filter(idea => idea.id === state.ideas.selectedIndex)[0]
})

const mapDispatchToProps = dispatch => ({
  saveIdeaHandler: (idea, workspaceId) => fetchPostIdeaCall(dispatch, idea, workspaceId)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeaForm)
