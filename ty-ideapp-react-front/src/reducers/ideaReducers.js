import { FETCH_IDEAS } from '../actions/types';

const ideas = (state = [], action) => {
  switch (action.type) {
    case FETCH_IDEAS:
      return action.ideas;
    default:
      return state;
  }
}

export default ideas;
