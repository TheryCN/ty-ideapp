import axios from 'axios';
import { FETCH_IDEAS, SELECT_IDEA, SELECT_IDEA_ACTION_TYPE, SAVE_IDEA } from './types'

export const fetchIdeas = ideas => ({
  type: FETCH_IDEAS,
  ideas
})

export const selectIdea = selectedIndex => ({
  type: SELECT_IDEA,
  selectedIndex
})

export const selectIdeaActionType = actionType => ({
  type: SELECT_IDEA_ACTION_TYPE,
  actionType
})

export const saveIdea = idea => ({
  type: SAVE_IDEA,
  idea
})

export const fetchIdeasCall = (dispatch, workspaceId) => {
  axios.get('/api/workspaces/'+workspaceId+'/ideas').then(response => {
    dispatch(fetchIdeas(response.data._embedded.ideas));
  });
}
