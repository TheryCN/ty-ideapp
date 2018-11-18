import { connect } from 'react-redux';
import Idea from '../components/Idea';

const mapStateToProps = state => ({
  ideas: state.ideas,
  selectedIndex: state.selectedIndex
})

const mapDispatchToProps = dispatch => ({
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Idea)
