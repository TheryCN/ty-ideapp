import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Avatar from '@material-ui/core/Avatar';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

const styles = {
  avatar: {
    margin: 10,
    cursor: 'pointer'
  }
};

class UserHeader extends Component {

  state = {
    anchorEl: null,
  };

  handleClick = event => {
    this.setState({ anchorEl: event.currentTarget });
  };

  handleClose = () => {
    this.setState({ anchorEl: null });
  };

  handleLogout = () => {
    this.props.logoutHandler();
    this.handleClose();
  }

  render() {
    const { anchorEl } = this.state;
    const firstLetter = this.props.loggedUser.username.charAt(0).toUpperCase();

    return (
      <Grid container justify="center" alignItems="center">
        <Avatar style={styles.avatar} onClick={this.handleClick}>{firstLetter}</Avatar>
        {this.props.loggedUser.username}

        <Menu
          id="simple-menu"
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={this.handleClose}
        >
          <MenuItem onClick={this.handleLogout}>Logout</MenuItem>
        </Menu>
      </Grid>
    );
  }
}

export default UserHeader;
