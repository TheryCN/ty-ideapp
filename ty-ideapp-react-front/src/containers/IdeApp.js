import { connect } from 'react-redux';
import axios from 'axios';

import IdeApp from '../components/IdeApp';
import { fetchLoggedUser, beforeFetchLoggedUser } from '../actions/userActions';

export const loadLoggedUserCall = (dispatch) => {
  dispatch(beforeFetchLoggedUser())
  axios.get(process.env.REACT_APP_BACKEND+'/api/user/').then(response => {
    dispatch(fetchLoggedUser(response.data));
  }, error => {
    dispatch(fetchLoggedUser(undefined));
  });
}

const mapStateToProps = state => ({
  loggedUser: state.users.loggedUser,
  loggedUserLoaded: state.users.loggedUserLoaded
})

const mapDispatchToProps = dispatch => ({
  loadLoggedUserHandler: () => loadLoggedUserCall(dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeApp)
