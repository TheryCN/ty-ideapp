import axios from 'axios';
import { FETCH_WORKSPACES, SELECT_WORKSPACE, UPDATE_COUNTER_WORKSPACE, ADD_WORKSPACE, EDIT_WORKSPACE, DELETE_WORKSPACE } from './types';

export const fetchWorkspaces = workspaces => ({
  type: FETCH_WORKSPACES,
  workspaces
});

export const selectWorkspace = selectedIndex => ({
  type: SELECT_WORKSPACE,
  selectedIndex
});

export const updateWorkspaceCounter = (id, counter) => ({
  type: UPDATE_COUNTER_WORKSPACE,
  id,
  counter
})

export const addWorkspace = workspace => ({
  type: ADD_WORKSPACE,
  workspace
});

export const editWorkspace = workspace => ({
  type: EDIT_WORKSPACE,
  workspace
});

export const deleteWorkspace = workspaceId => ({
  type: DELETE_WORKSPACE,
  workspaceId
});

export const fetchWorkspacesCall = (dispatch) => {
  axios.get(process.env.REACT_APP_BACKEND+'/api/workspaces/').then(response => {
    dispatch(fetchWorkspaces(response.data));
  });
}
