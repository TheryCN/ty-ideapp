import { connect } from 'react-redux';
import { fetchIdeasCall, selectIdea } from '../actions/ideaActions';
import Ideas from '../components/Ideas';

const mapStateToProps = state => ({
  ideas: state.ideas.ideas,
  selectedIndex: state.ideas.selectedIndex,
  workspaceId: state.workspaces.selectedIndex
})

const mapDispatchToProps = dispatch => ({
  loadHandler: workspaceId => fetchIdeasCall(dispatch, workspaceId),
  clickHandler: index => dispatch(selectIdea(index))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ideas)
