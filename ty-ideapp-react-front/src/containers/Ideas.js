import { connect } from 'react-redux';
import { fetchIdeasCall, selectIdea } from '../actions/ideaActions';
import Ideas from '../components/Ideas';

const mapStateToProps = state => ({
  ideas: state.ideas,
  selectedIndex: state.selectedIndex
})

const mapDispatchToProps = dispatch => ({
  loadHandler: workspaceId => fetchIdeasCall(dispatch, workspaceId),
  clickHandler: index => dispatch(selectIdea(index))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ideas)
