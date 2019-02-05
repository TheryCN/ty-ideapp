import { combineReducers } from 'redux';
import ideas from './ideaReducers';
import workspaces from './workspaceReducers';
import notifications from './notificationReducers';
import users from './userReducers';

export default combineReducers({
  ideas,
  workspaces,
  notifications,
  users
})
