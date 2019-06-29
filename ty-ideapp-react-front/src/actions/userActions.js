import { FETCH_LOGGED_USER, BEFORE_FETCH_LOGGED_USER, LOGIN_REQUESTED, LOGIN_ERROR, REGISTRATION_REQUESTED, REGISTRATION_ERROR } from './types';

export const loginRequested = formData => ({
  type: LOGIN_REQUESTED,
  formData
});

export const loginError = loginError => ({
  type: LOGIN_ERROR,
  loginError
});

export const registrationRequested = user => ({
  type: REGISTRATION_REQUESTED,
  user
});

export const registrationError = registrationError => ({
  type: REGISTRATION_ERROR,
  registrationError
});

export const beforeFetchLoggedUser = () => ({
  type: BEFORE_FETCH_LOGGED_USER
});

export const fetchLoggedUser = loggedUser => ({
  type: FETCH_LOGGED_USER,
  loggedUser
});
