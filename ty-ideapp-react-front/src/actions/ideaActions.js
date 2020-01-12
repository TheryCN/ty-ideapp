import axios from 'axios';
import { FETCH_IDEAS, SELECT_IDEA, SELECT_IDEA_ACTION_TYPE, ADD_IDEA, EDIT_IDEA, DELETE_IDEA } from './types';

export const fetchIdeas = ideas => ({
  type: FETCH_IDEAS,
  ideas
});

export const selectIdea = selectedIndex => ({
  type: SELECT_IDEA,
  selectedIndex
});

export const selectIdeaActionType = actionType => ({
  type: SELECT_IDEA_ACTION_TYPE,
  actionType
});

export const addIdea = idea => ({
  type: ADD_IDEA,
  idea
});

export const editIdea = idea => ({
  type: EDIT_IDEA,
  idea
});

export const deleteIdea = ideaId => ({
  type: DELETE_IDEA,
  ideaId
});

export const fetchIdeasCall = (dispatch, workspaceId) => {
  axios.get(process.env.REACT_APP_BACKEND+'/api/data-rest/workspaces/'+workspaceId+'/ideas').then(response => {
    dispatch(fetchIdeas(response.data._embedded.ideas));
  });
}
