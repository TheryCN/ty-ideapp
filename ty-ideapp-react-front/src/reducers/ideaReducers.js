import { FETCH_IDEAS, SELECT_IDEA, SELECT_IDEA_ACTION_TYPE, SAVE_IDEA } from '../actions/types';

const ideas = (state = {ideas: [], selectedIndex: 0}, action) => {
  switch (action.type) {
    case SELECT_IDEA:
      return Object.assign({}, state, {selectedIndex: action.selectedIndex});
    case FETCH_IDEAS:
      return Object.assign({}, state, {ideas: action.ideas});
    case SELECT_IDEA_ACTION_TYPE:
      return Object.assign({}, state, {actionType: action.actionType});
    case SAVE_IDEA:
      let ideas = [];
      ideas.concat(state.ideas);
      ideas.push(action.idea);
      return Object.assign({}, state, {ideas: ideas, selectedIndex: action.idea.id, actionType: undefined});
    default:
      return state;
  }
}

export default ideas;
