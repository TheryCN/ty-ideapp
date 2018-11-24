import React, { Component } from 'react';
import moment from 'moment';
import Grid from '@material-ui/core/Grid';
import IdeaBackground from '../assets/idea-background.jpg';

const ideaBackgroundStyle = {
  background: "url(" + IdeaBackground + ") no-repeat fixed center",
  height: '80vh'
};

class Idea extends Component {

  render() {
    const idea = this.props.ideas[this.props.selectedIndex];
      if(idea) {
      return (
        <div className="idea" style={ideaBackgroundStyle}>
          <Grid container>
            <Grid item xs={12} sm={12}>
              <div>{idea.name}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{idea.subTitle}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{idea.description}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{idea.rating}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{idea.feasibility}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{moment(idea.createdOn).format('LLL')}</div>
            </Grid>
            <Grid item xs={12} sm={12}>
              <div>{moment(idea.updatedOn).format('LLL')}</div>
            </Grid>
          </Grid>
        </div>
      );
    } else {
      return <div />;
    }
  }
}

export default Idea;
