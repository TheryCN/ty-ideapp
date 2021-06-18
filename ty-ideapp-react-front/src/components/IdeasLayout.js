import React from 'react';
import FormControl from '@material-ui/core/FormControl';
import Divider from '@material-ui/core/Divider';
import Grid from '@material-ui/core/Grid';

import IdeaWorkspaces from '../containers/IdeaWorkspaces.js';
import Ideas from '../containers/Ideas.js';
import Idea from '../containers/Idea.js';

const styles = {
  left: {
    width: '100%',
    maxWidth: 300
  },
};

const IdeasLayout = () => (
  <Grid container className="layout">
    <Grid key={1} item xs={3} sm={3} className="left-layout" style={styles.left}>
      <FormControl>
        <IdeaWorkspaces />
        <Divider />
        <Ideas />
      </FormControl>
    </Grid>

    <Grid key={2} item xs={9} sm={9} className="mid-layout">
      <Idea />
    </Grid>
  </Grid>
)

export default IdeasLayout;
