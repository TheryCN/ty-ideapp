import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';

import FormControl from '@material-ui/core/FormControl';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import PasswordEdit from '../containers/PasswordEdit';

class ProfileLayout extends Component {

  render() {
    return (<Grid container className="layout">
      <Grid key={1} item xs={3} sm={3}>
        <FormControl>
          <ListItem button selected={true}>
            <ListItemText primary="Password" />
          </ListItem>
        </FormControl>
      </Grid>

      <Grid key={2} item xs={9} sm={9}>
        <PasswordEdit />
      </Grid>
    </Grid>);
  }

}

export default ProfileLayout;
