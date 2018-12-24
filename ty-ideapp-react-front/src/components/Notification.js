import React, { Component } from 'react';
import Snackbar from '@material-ui/core/Snackbar';

class Notification extends Component {

  state = {
    open: false,
    vertical: 'top',
    horizontal: 'right',
  };

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(this.props.message !== undefined && this.props.message !== prevProps.message) {
      this.setState({ ...this.state, open: true });
    }
  }

  handleClose = () => {
    this.setState({ open: false });
    this.props.clearNotification();
  };

  render() {
    const { vertical, horizontal, open } = this.state;
    return (
      <Snackbar
        anchorOrigin={{ vertical, horizontal }}
        open={open}
        onClose={this.handleClose}
        ContentProps={{
          'aria-describedby': 'message-id',
        }}
        message={<span id="message-id">{this.props.message}</span>}
      />
    );
  }
}

export default Notification;
