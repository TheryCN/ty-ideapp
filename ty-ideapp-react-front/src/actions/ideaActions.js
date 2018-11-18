import axios from 'axios';
import { FETCH_IDEAS, SELECT_IDEA } from './types'

export const fetchIdeas = ideas => ({
  type: FETCH_IDEAS,
  ideas
})

export const selectIdea = selectedIndex => ({
  type: SELECT_IDEA,
  selectedIndex
})

export const fetchIdeasCall = (dispatch, workspaceId) => {
  axios.get('/api/workspaces/'+workspaceId+'/ideas').then(response => {
    dispatch(fetchIdeas(response.data._embedded.ideas));
  });
}
