import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';

import IdeaBackground from '../assets/idea-background.jpg';
import IdeaView from './IdeaView.js';
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
          <div className="idea-content">
            <IdeaView idea={idea} />
          </div>
        </div>
      );
    } else {
      return <div />;
    }
  }
}

export default Idea;
