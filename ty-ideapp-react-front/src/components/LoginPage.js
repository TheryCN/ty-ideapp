import React from 'react';
import Grid from '@material-ui/core/Grid';

import Login from '../containers/Login.js';
import Registration from '../containers/Registration.js'

const LoginPage = () => {
  return (
    <Grid container className="layout">
      <Grid key={1} item xs={3} sm={3}>
      </Grid>
      <Grid key={2} item xs={3} sm={3}>
        <Login />
      </Grid>
      <Grid key={3} item xs={3} sm={3}>
        <Registration />
      </Grid>
      <Grid key={4} item xs={3} sm={3}>
      </Grid>
    </Grid>
  );
}

export default LoginPage;
