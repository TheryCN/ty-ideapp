import { connect } from 'react-redux';
import axios from 'axios';

import UserHeader from '../components/UserHeader';
import { fetchLoggedUser } from '../actions/userActions';

export const logoutCall = (dispatch) => {
  axios.get(process.env.REACT_APP_BACKEND+'/api/logout').then(response => {
    dispatch(fetchLoggedUser(undefined));
  });
}

const mapStateToProps = state => ({
  loggedUser: state.users.loggedUser
})

const mapDispatchToProps = dispatch => ({
  logoutHandler: () => logoutCall(dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserHeader)
