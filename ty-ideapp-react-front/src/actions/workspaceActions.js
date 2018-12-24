import { FETCH_WORKSPACES, SELECT_WORKSPACE, UPDATE_COUNTER_WORKSPACE } from './types';

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
