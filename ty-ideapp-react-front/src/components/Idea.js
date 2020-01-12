import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import ViewIcon from '@material-ui/icons/ViewArray';

import IdeaView from './IdeaView';
import IdeaEdit from '../containers/IdeaEdit';
import IdeaAdd from '../containers/IdeaAdd';
import './Idea.css';

export const ACTION_TYPE_ADD = 'ADD';
export const ACTION_TYPE_EDIT = 'EDIT';

class Idea extends Component {

  render() {
    let ideaBackgroundStyle = {
      background: "url(/images/idea-background-1.jpg) no-repeat fixed center"
    };
    const idea = this.props.ideas.filter(idea => idea.id === this.props.selectedIndex)[0];
    if(idea) {
      ideaBackgroundStyle.background = "url(/images/idea-background-" + idea.id%6 + ".jpg) no-repeat fixed center";
      let ideaViewAction;
      switch(this.props.actionType) {
        case ACTION_TYPE_EDIT:
          ideaViewAction = <IdeaEdit />;
          break;
        case ACTION_TYPE_ADD:
          ideaViewAction = <IdeaAdd />;
          break;
        default:
          ideaViewAction = <IdeaView idea={idea} />;
          break;
      }
      return (
        <div className="idea" style={ideaBackgroundStyle}>
          <div className="actions">
            <IconButton aria-label="View Idea" onClick={() => this.props.selectActionTypeHandler(undefined)}>
              <ViewIcon />
            </IconButton>
            <IconButton aria-label="Edit Idea" onClick={() => this.props.selectActionTypeHandler(ACTION_TYPE_EDIT)}>
              <EditIcon />
            </IconButton>
            <IconButton aria-label="Add Idea" onClick={() => this.props.selectActionTypeHandler(ACTION_TYPE_ADD)}>
              <AddIcon />
            </IconButton>
            <IconButton aria-label="Delete Idea" onClick={() => this.props.deleteIdea(this.props.selectedIndex)}>
              <DeleteIcon />
            </IconButton>
          </div>
          <div className="idea-content">
            {ideaViewAction}
          </div>
        </div>
      );
    } else {
      return (
        <div className="idea" style={ideaBackgroundStyle}>
          <div className="idea-content">
            <IdeaAdd />
          </div>
        </div>
      );
    }
  }
}

export default Idea;
