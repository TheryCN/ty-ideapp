import { FETCH_IDEAS, SELECT_IDEA } from '../actions/types';

const ideas = (state = {ideas: [], selectedIndex: 0}, action) => {
  switch (action.type) {
    case SELECT_IDEA:
      return Object.assign({}, state, {selectedIndex: action.selectedIndex});
    case FETCH_IDEAS:
      return Object.assign({}, state, {ideas: action.ideas});
    default:
      return state;
  }
}

export default ideas;
