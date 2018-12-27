import { FETCH_WORKSPACES, SELECT_WORKSPACE, UPDATE_COUNTER_WORKSPACE, ADD_WORKSPACE, EDIT_WORKSPACE, DELETE_WORKSPACE } from '../actions/types';

const workspaces = (state = {workspaces: [], selectedIndex: 0}, action) => {
  switch (action.type) {
    case SELECT_WORKSPACE:
      return Object.assign({}, state, {selectedIndex: action.selectedIndex});
    case FETCH_WORKSPACES:
      return Object.assign({}, state, {workspaces: action.workspaces, selectedIndex: action.workspaces[0].id});
    case UPDATE_COUNTER_WORKSPACE:
      const workspaces = state.workspaces.map(workspace => {
        if(workspace.id === action.id) {
          workspace.nbIdeas = action.counter;
        }
        return workspace;
      });
      return Object.assign({}, state, {workspaces: workspaces});
    case ADD_WORKSPACE:
      let addWorkspaces = [];
      addWorkspaces = addWorkspaces.concat(state.workspaces);
      addWorkspaces.push(action.workspace);
      return Object.assign({}, state, {workspaces: addWorkspaces, selectedIndex: action.workspace.id});
    case EDIT_WORKSPACE:
      let editWorkspaces = [];
      editWorkspaces = editWorkspaces.concat(state.workspaces);
      return Object.assign({}, state, {workspaces: editWorkspaces.map(workspace => (workspace.id === action.workspace.id) ? action.workspace : workspace), selectedIndex: action.workspace.id});
    case DELETE_WORKSPACE:
      let deleteWorkspaces = [];
      deleteWorkspaces = deleteWorkspaces.concat(state.workspaces);
      let workspaceIndex = deleteWorkspaces.findIndex(workspace => workspace.id === workspace.workspaceId);
      deleteWorkspaces.splice(workspaceIndex,1);
      return Object.assign({}, state, {workspaces: deleteWorkspaces, selectedIndex: state.workspaces[0].id});
    default:
      return state;
  }
}

export default workspaces;
