import React from 'react';
import FormControl from '@material-ui/core/FormControl';
import Divider from '@material-ui/core/Divider';

import Workspaces from '../containers/Workspaces.js';
import Ideas from '../containers/Ideas.js';

const LeftLayout = () => (
  <div>
    <FormControl>
      <Workspaces />
      <Divider />
      <Ideas />
    </FormControl>
  </div>
)

export default LeftLayout;
