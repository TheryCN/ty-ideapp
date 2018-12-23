import { NOTIFY } from '../actions/types';

const notifications = (state = {message: undefined}, action) => {
  switch (action.type) {
    case NOTIFY:
      console.log(action.message);
      return Object.assign({}, state, {message: action.message});
    default:
      return state;
  }
}

export default notifications;
