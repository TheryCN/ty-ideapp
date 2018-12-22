import { connect } from 'react-redux';
import axios from 'axios';

import Idea from '../components/Idea';
import { selectIdeaActionType, deleteIdea } from '../actions/ideaActions';

export const deleteIdeaCall = (dispatch, ideaId) => {
  axios.delete('/api/ideas/'+ideaId).then(response => {
    dispatch(deleteIdea(ideaId));
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
