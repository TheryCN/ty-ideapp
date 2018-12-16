import { connect } from 'react-redux';
import axios from 'axios';

import IdeaAdd from '../components/IdeaAdd';
import { saveIdea } from '../actions/ideaActions';

export const fetchPostIdeaCall = (dispatch, idea) => {
  axios.post('/api/ideas', idea).then(response => {
    dispatch(saveIdea(response.data));
  });
}

const mapStateToProps = state => ({

})

const mapDispatchToProps = dispatch => ({
  saveIdeaHandler: (idea) => fetchPostIdeaCall(dispatch, idea)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdeaAdd)
