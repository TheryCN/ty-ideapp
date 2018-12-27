import React from 'react';
import FormControl from '@material-ui/core/FormControl';
import Divider from '@material-ui/core/Divider';

import IdeaWorkspaces from '../containers/IdeaWorkspaces.js';
import Ideas from '../containers/Ideas.js';

const LeftLayout = () => (
  <div>
    <FormControl>
      <IdeaWorkspaces />
      <Divider />
      <Ideas />
    </FormControl>
  </div>
)

export default LeftLayout;
