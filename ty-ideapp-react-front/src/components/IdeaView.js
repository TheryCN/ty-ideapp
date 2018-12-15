import React from 'react';
import moment from 'moment';

const IdeaView = ({ idea }) => (
  <div>
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
)

export default IdeaView;
