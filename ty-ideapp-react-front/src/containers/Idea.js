import { connect } from 'react-redux';
import axios from 'axios';

import Idea from '../components/Idea';
import { selectIdeaActionType, deleteIdea } from '../actions/ideaActions';
import { notify } from '../actions/notificationActions';

export const deleteIdeaCall = (dispatch, ideaId) => {
  axios.delete(process.env.REACT_APP_BACKEND+'/api/ideas/'+ideaId).then(response => {
    dispatch(deleteIdea(ideaId));
    dispatch(notify("Idea deleted"));
  });
}

const mapStateToProps = state => ({
  ideas: state.ideas.ideas,
  selectedIndex: state.ideas.selectedIndex,
  actionType: state.ideas.actionType
})

const mapDispatchToProps = dispatch => ({
  selectActionTypeHandler: (actionType) => dispatch(selectIdeaActionType(actionType)),
  deleteIdea: (id) => deleteIdeaCall(dispatch, id)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Idea)
