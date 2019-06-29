import React, { Component } from 'react';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import './Login.css';

class RegistrationForm extends Component {

  constructor(props) {
    super(props);
    this.state = {username: "", email: "", password: ""};
  }

  handleChange = name => event => {
    this.setState({...this.state, [name]: event.target.value})
  }

  handleSubmit = (event) => {
      event.preventDefault();
      this.props.registrationCallHandler(this.state)
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit} className="login-form">
        <h1>New user ? Welcome !</h1>
        <div className="title-content">
          <TextField
            label="Username"
            onChange={this.handleChange('username')}
            value={this.state.username}
          />
        </div>
        <div className="title-content">
          <TextField
            label="Email"
            onChange={this.handleChange('email')}
            value={this.state.email}
          />
        </div>
        <div>
          <TextField
            label="Password"
            type="password"
            autoComplete="current-password"
            margin="normal"
            onChange={this.handleChange('password')}
            value={this.state.password}
          />
        </div>

        <div>
          <Button className="save-button" variant="contained" type="submit">
            Register
          </Button>
        </div>

        <div>
          {this.props.registrationError}
        </div>
      </form>
    );
  }
}

export default RegistrationForm;
