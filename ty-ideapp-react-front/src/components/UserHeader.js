import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Avatar from '@material-ui/core/Avatar';

const styles = {
  avatar: {
    margin: 10
  }
};

class UserHeader extends Component {

  render() {
    const firstLetter = this.props.loggedUser.username.charAt(0).toUpperCase();
    return (
      <Grid container justify="center" alignItems="center">
        <Avatar style={styles.avatar}>{firstLetter}</Avatar>
        {this.props.loggedUser.username}
      </Grid>
    );
  }
}

export default UserHeader;
