import { connect } from 'react-redux';
import axios from 'axios';

import UserHeader from '../components/UserHeader';
import { fetchLoggedUser } from '../actions/userActions';

const mapStateToProps = state => ({
  loggedUser: state.users.loggedUser
})

const mapDispatchToProps = dispatch => ({
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserHeader)
