import { connect } from 'react-redux';
import axios from 'axios';

import IdeaForm from '../components/IdeaForm';
import { addIdea } from '../actions/ideaActions';

export const fetchPostIdeaCall = (dispatch, idea, workspaceId) => {
  idea.workspaceId = workspaceId;
  axios.post('/api/idea/', idea).then(response => {
    dispatch(addIdea(response.data));
  });
}

const mapStateToProps = state => ({
  workspaceId: state.workspaces.selectedIndex,
  idea: {name: "", subTitle: "", feasibility: "NORMAL"}
})

const mapDispatchToProps = dispatch => ({
  saveIdeaHandler: (idea, workspaceId) => fetchPostIdeaCall(dispatch, idea, workspaceId)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeaForm)
