import React from 'react';
import PropTypes from 'prop-types';

import moment from 'moment';
import Rating from './Rating';
import IdeaMap from './IdeaMap';

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

    <IdeaMap localizations={idea.localizations} readOnly={true} />
  </div>
)

IdeaView.propTypes = {
  idea: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    description: PropTypes.string,
    rating: PropTypes.number,
    feasibility: PropTypes.string,
    createdOn: PropTypes.string,
    updatedOn: PropTypes.string
  }).isRequired
};

export default IdeaView;
