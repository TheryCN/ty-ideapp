import { FETCH_WORKSPACES, SELECT_WORKSPACE } from './types';

export const fetchWorkspaces = workspaces => ({
  type: FETCH_WORKSPACES,
  workspaces
});

export const selectWorkspace = selectedIndex => ({
  type: SELECT_WORKSPACE,
  selectedIndex
});
