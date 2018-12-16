import { FETCH_WORKSPACES, SELECT_WORKSPACE } from '../actions/types';

const workspaces = (state = {workspaces: [], selectedIndex: 0}, action) => {
  switch (action.type) {
    case SELECT_WORKSPACE:
      return Object.assign({}, state, {selectedIndex: action.selectedIndex});
    case FETCH_WORKSPACES:
      return Object.assign({}, state, {workspaces: action.workspaces, selectedIndex: action.workspaces[0].id});
    default:
      return state;
  }
}

export default workspaces;
