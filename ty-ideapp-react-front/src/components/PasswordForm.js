import React, { Component } from 'react';
import PropTypes from 'prop-types';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

class PasswordForm extends Component {

  state = {
    oldPassword: "",
    newPassword: ""
  }

  handleChange = name => event => {
    this.setState({...this.state, [name]: event.target.value})
  }

  render() {
    return (
      <div>
        <div className="title-content">
          <TextField
            label="Old password"
            type="password"
            autoComplete="current-password"
            onChange={this.handleChange('oldPassword')}
          />
        </div>
        <div>
          <TextField
            label="New password"
            type="password"
            autoComplete="current-password"
            margin="normal"
            onChange={this.handleChange('newPassword')}
          />
        </div>

        <div>
          <Button className="save-button" variant="contained" type="submit" onClick={() => this.props.savePasswordHandler(this.state.oldPassword, this.state.newPassword)}>
            Save password
          </Button>
        </div>
      </div>
    );
  }
}

PasswordForm.propTypes = {
  savePasswordHandler: PropTypes.func.isRequired
};

export default PasswordForm;
