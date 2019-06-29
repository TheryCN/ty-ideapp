import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import './Login.css';

class LoginForm extends Component {

  constructor(props) {
    super(props);
    this.state = {username: "", password: ""};
  }

  handleChange = name => event => {
    this.setState({...this.state, [name]: event.target.value})
  }

  handleSubmit = (event) => {
      event.preventDefault();

      var formData = new FormData();
      formData.set('username', this.state.username);
      formData.set('password', this.state.password);

      this.props.loginCallHandler(formData);
  }

  render() {
    if (this.props.loggedUser !== undefined) {
      return <Redirect to='/' />
    }
    return (
      <form onSubmit={this.handleSubmit} className="login-form">
        <h1>Already have an account ?</h1>
        <div className="title-content">
          <TextField
            label="Username"
            onChange={this.handleChange('username')}
            value={this.state.username}
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
            Login
          </Button>
        </div>

        <div>
          {this.props.loginError}
        </div>
      </form>
    );
  }
}

export default LoginForm;
