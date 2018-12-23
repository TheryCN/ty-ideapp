import React from 'react';
import moment from 'moment';
import Rating from './Rating';

const IdeaView = ({ idea }) => (
  <div>
    <div className="title-content">
      <span className="title">{idea.name}</span>
      <span className="subtitle">{idea.subTitle}</span>
    </div>
    <div>{idea.description}</div>
    <Rating value={idea.rating} max={5} readOnly={true} />
    <div>{idea.feasibility}</div>
    <div>{moment(idea.createdOn).format('LLL')}</div>
    <div>{moment(idea.updatedOn).format('LLL')}</div>
  </div>
)

export default IdeaView;
