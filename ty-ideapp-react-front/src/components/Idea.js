import React, { Component } from 'react';

class Idea extends Component {

  render() {
    const idea = this.props.ideas[this.props.selectedIndex];
      if(idea) {
      return (
        <div>
          <div>{idea.name}</div>
          <div>{idea.description}</div>
          <div>{idea.rating}</div>
          <div>{idea.feasibility}</div>
          <div>{idea.createdOn}</div>
          <div>{idea.updatedOn}</div>
        </div>
      );
    } else {
      return <div />;
    }
  }
}

export default Idea;
