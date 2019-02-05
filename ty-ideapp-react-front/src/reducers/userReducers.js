import { FETCH_LOGGED_USER, BEFORE_FETCH_LOGGED_USER } from '../actions/types';

const users = (state = {loggedUser: undefined, loggedUserLoaded: false}, action) => {
  switch (action.type) {
    case BEFORE_FETCH_LOGGED_USER:
      return Object.assign({}, state, {loggedUserLoaded: false});
    case FETCH_LOGGED_USER:
      return Object.assign({}, state, {loggedUser: action.loggedUser, loggedUserLoaded: true});
    default:
      return state;
  }
}

export default users;
