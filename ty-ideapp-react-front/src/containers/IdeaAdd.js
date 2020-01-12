import { connect } from 'react-redux';
import axios from 'axios';

import IdeaForm from '../components/IdeaForm';
import { addIdea } from '../actions/ideaActions';
import { notify } from '../actions/notificationActions';

export const fetchPostIdeaCall = (dispatch, idea, workspaceId) => {
  idea.workspaceId = workspaceId;
  axios.post(process.env.REACT_APP_BACKEND+'/api/ideas/', idea).then(response => {
    dispatch(addIdea(response.data));
    dispatch(notify("Idea added"));
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
