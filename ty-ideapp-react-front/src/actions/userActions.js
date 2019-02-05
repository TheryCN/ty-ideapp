import { FETCH_LOGGED_USER, BEFORE_FETCH_LOGGED_USER } from './types';

export const beforeFetchLoggedUser = () => ({
  type: BEFORE_FETCH_LOGGED_USER
});

export const fetchLoggedUser = loggedUser => ({
  type: FETCH_LOGGED_USER,
  loggedUser
});
