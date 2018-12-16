import { connect } from 'react-redux';
import Idea from '../components/Idea';
import { selectIdeaActionType } from '../actions/ideaActions';

const mapStateToProps = state => ({
  ideas: state.ideas,
  selectedIndex: state.selectedIndex,
  actionType: state.actionType
})

const mapDispatchToProps = dispatch => ({
  selectActionTypeHandler: (actionType) => dispatch(selectIdeaActionType(actionType))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Idea)
