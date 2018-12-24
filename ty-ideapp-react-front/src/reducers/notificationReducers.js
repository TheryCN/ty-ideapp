import { NOTIFY, CLEAR_NOTIFICATION } from '../actions/types';

const notifications = (state = {message: undefined}, action) => {
  switch (action.type) {
    case NOTIFY:
      console.log(action.message);
      return Object.assign({}, state, {message: action.message});
    case CLEAR_NOTIFICATION:
      return Object.assign({}, state, {message: undefined});
    default:
      return state;
  }
}

export default notifications;
