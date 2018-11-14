import { connect } from 'react-redux';
import { fetchIdeasCall } from '../actions/ideaActions';
import Ideas from '../components/Ideas';

const mapStateToProps = state => ({
  ideas: state
})

const mapDispatchToProps = dispatch => ({
  loadHandler: workspaceId => fetchIdeasCall(dispatch, workspaceId)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ideas)
