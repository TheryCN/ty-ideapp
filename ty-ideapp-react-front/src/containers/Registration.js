import { connect } from 'react-redux';

import RegistrationForm from '../components/RegistrationForm';
import { registrationRequested } from '../actions/userActions';

const mapStateToProps = state => ({
  registrationError: state.users.registrationError
})

const mapDispatchToProps = dispatch => ({
  registrationCallHandler: (user) => dispatch(registrationRequested(user))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RegistrationForm)
