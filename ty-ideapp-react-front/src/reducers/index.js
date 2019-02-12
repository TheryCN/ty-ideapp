import { combineReducers } from 'redux';
import ideas from './ideaReducers';
import workspaces from './workspaceReducers';
import notifications from './notificationReducers';
import users from './userReducers';
import { RESET_APP } from '../actions/types';

const appReducer = combineReducers({
  ideas,
  workspaces,
  notifications,
  users
});

const rootReducer = (state, action) => {
  if (action.type === RESET_APP) {
    state = undefined;
  }

  return appReducer(state, action);
}

export default rootReducer;
