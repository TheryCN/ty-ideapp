import { FETCH_LOGGED_USER, BEFORE_FETCH_LOGGED_USER, LOGIN_ERROR, REGISTRATION_ERROR } from '../actions/types';

const users = (state = {loggedUser: undefined, loggedUserLoaded: false}, action) => {
  switch (action.type) {
    case REGISTRATION_ERROR:
      return Object.assign({}, state, {loggedUser: undefined, loggedUserLoaded: false, registrationError: action.registrationError});
    case LOGIN_ERROR:
      return Object.assign({}, state, {loggedUser: undefined, loggedUserLoaded: false, loginError: action.loginError});
    case BEFORE_FETCH_LOGGED_USER:
      return Object.assign({}, state, {loggedUserLoaded: false});
    case FETCH_LOGGED_USER:
      return Object.assign({}, state, {loggedUser: action.loggedUser, loggedUserLoaded: true});
    default:
      return state;
  }
}

export default users;
