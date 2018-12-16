import { combineReducers } from 'redux';
import ideas from './ideaReducers';
import workspaces from './workspaceReducers';

export default combineReducers({
  ideas,
  workspaces
})
