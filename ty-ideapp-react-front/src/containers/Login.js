import { connect } from 'react-redux';
import axios from 'axios';

import LoginForm from '../components/LoginForm';
import { fetchLoggedUser } from '../actions/userActions';
import { resetApp } from '../actions/rootActions';

export const loginCall = (dispatch, formData) => {
  axios.post(process.env.REACT_APP_BACKEND+'/api/login', formData).then(response => {
    dispatch(resetApp());
    dispatch(fetchLoggedUser(response.data));
  });
}

const mapStateToProps = state => ({
  loggedUser: state.users.loggedUser
})

const mapDispatchToProps = dispatch => ({
  loginCallHandler: (formData) => loginCall(dispatch, formData)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginForm)
