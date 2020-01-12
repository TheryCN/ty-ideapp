import React from 'react';
import Grid from '@material-ui/core/Grid';

import Login from '../containers/Login.js';
import Registration from '../containers/Registration.js';
import './LoginPage.css';

const LoginPage = () => {
  const ideaBackgroundStyle = {
    background: "url(/images/idea-background-1.jpg) no-repeat fixed center",
    backgroundSize: "cover"
  };
  return (
    <div style={ideaBackgroundStyle}>
      <Grid container className="layout">
        <Grid key={1} item xs={3} sm={3}>
        </Grid>
        <Grid key={2} item xs={3} sm={3} className="login-element">
          <Login />
        </Grid>
        <Grid key={3} item xs={3} sm={3} className="login-element">
          <Registration />
        </Grid>
        <Grid key={4} item xs={3} sm={3}>
        </Grid>
      </Grid>
    </div>
  );
}

export default LoginPage;
