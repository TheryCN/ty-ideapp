import React, { Component } from 'react';
import moment from 'moment';
import IdeaBackground from '../assets/idea-background.jpg';
import IconButton from '@material-ui/core/IconButton';
import Avatar from '@material-ui/core/Avatar';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';

import './Idea.css';

const ideaBackgroundStyle = {
  background: "url(" + IdeaBackground + ") no-repeat fixed center"
};

class Idea extends Component {

  render() {
    const idea = this.props.ideas[this.props.selectedIndex];
      if(idea) {
      return (
        <div className="idea" style={ideaBackgroundStyle}>
          <div className="actions">
            <IconButton aria-label="Edit Idea">
              <EditIcon />
            </IconButton>
            <IconButton aria-label="Add Idea">
              <AddIcon />
            </IconButton>
            <IconButton aria-label="Delete Idea">
              <DeleteIcon />
            </IconButton>
          </div>
          <div container className="idea-content">
            <div className="title-content">
              <span className="title">{idea.name}</span>
              <span className="subtitle">{idea.subTitle}</span>
            </div>
            <div>{idea.description}</div>
            <div>{idea.rating}</div>
            <div>{idea.feasibility}</div>
            <div>{moment(idea.createdOn).format('LLL')}</div>
            <div>{moment(idea.updatedOn).format('LLL')}</div>
          </div>
        </div>
      );
    } else {
      return <div />;
    }
  }
}

export default Idea;
