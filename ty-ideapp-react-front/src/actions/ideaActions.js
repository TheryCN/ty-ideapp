import axios from 'axios';
import { FETCH_IDEAS } from './types'

export const fetchIdeas = ideas => ({
  type: FETCH_IDEAS,
  ideas
})

export const fetchIdeasCall = (dispatch, workspaceId) => {
  axios.get('/api/workspaces/'+workspaceId+'/ideas').then(response => {
    dispatch(fetchIdeas(response.data._embedded.ideas));
  });
}
