import { connect } from 'react-redux';
import axios from 'axios';

import PasswordForm from '../components/PasswordForm';
import { notify } from '../actions/notificationActions';

export const savePasswordCall = (dispatch, oldPassword, newPassword) => {
  axios.patch(process.env.REACT_APP_BACKEND+'/api/users/password', {oldPassword: oldPassword, newPassword: newPassword}).then(response => {
    dispatch(notify("Password changed"));
  }, error => {
    dispatch(notify(error.response.data));
  });
}

const mapStateToProps = state => ({
})

const mapDispatchToProps = dispatch => ({
  savePasswordHandler: (oldPassword, newPassword) => savePasswordCall(dispatch, oldPassword, newPassword)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PasswordForm)
