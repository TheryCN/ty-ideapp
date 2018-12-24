import { connect } from 'react-redux';

import Notification from '../components/Notification';
import { clearNotification } from '../actions/notificationActions';

const mapStateToProps = state => ({
  message: state.notifications.message
})

const mapDispatchToProps = dispatch => ({
  clearNotification: () => dispatch(clearNotification())
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Notification)
