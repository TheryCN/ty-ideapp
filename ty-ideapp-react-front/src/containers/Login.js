import { connect } from 'react-redux';

import LoginForm from '../components/LoginForm';
import { loginRequested } from '../actions/userActions';

const mapStateToProps = state => ({
  loginError: state.users.loginError,
  loggedUser: state.users.loggedUser
})

const mapDispatchToProps = dispatch => ({
  loginCallHandler: (formData) => dispatch(loginRequested(formData))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginForm)
