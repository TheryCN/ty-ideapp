import { connect } from 'react-redux';
import Idea from '../components/Idea';
import { selectIdeaActionType } from '../actions/ideaActions';

const mapStateToProps = state => ({
  ideas: state.ideas.ideas,
  selectedIndex: state.ideas.selectedIndex,
  actionType: state.ideas.actionType
})

const mapDispatchToProps = dispatch => ({
  selectActionTypeHandler: (actionType) => dispatch(selectIdeaActionType(actionType))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Idea)
