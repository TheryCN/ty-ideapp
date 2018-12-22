import { FETCH_IDEAS, SELECT_IDEA, SELECT_IDEA_ACTION_TYPE, ADD_IDEA, EDIT_IDEA, DELETE_IDEA } from '../actions/types';

const ideas = (state = {ideas: [], selectedIndex: 0}, action) => {
  switch (action.type) {
    case SELECT_IDEA:
      return Object.assign({}, state, {selectedIndex: action.selectedIndex});
    case FETCH_IDEAS:
      return Object.assign({}, state, {ideas: action.ideas, selectedIndex: action.ideas[0].id});
    case SELECT_IDEA_ACTION_TYPE:
      return Object.assign({}, state, {actionType: action.actionType});
    case ADD_IDEA:
      let ideas = [];
      ideas = ideas.concat(state.ideas);
      ideas.push(action.idea);
      return Object.assign({}, state, {ideas: ideas, selectedIndex: action.idea.id, actionType: undefined});
    case EDIT_IDEA:
      let editIdeas = [];
      editIdeas = editIdeas.concat(state.ideas);
      return Object.assign({}, state, {ideas: editIdeas.map(idea => (idea.id === action.idea.id) ? action.idea : idea), actionType: undefined});
    case DELETE_IDEA:
      let deleteIdeas = [];
      deleteIdeas = deleteIdeas.concat(state.ideas);
      let ideaIndex = deleteIdeas.findIndex(idea => idea.id === action.ideaId);
      deleteIdeas.splice(ideaIndex,1);
      return Object.assign({}, state, {ideas: deleteIdeas, actionType: undefined, selectedIndex: state.ideas[0].id});
    default:
      return state;
  }
}

export default ideas;
