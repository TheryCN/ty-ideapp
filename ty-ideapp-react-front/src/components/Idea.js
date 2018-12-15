import React, { Component } from 'react';
import moment from 'moment';
import IdeaBackground from '../assets/idea-background.jpg';

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
