import React, { Component } from 'react';
import PropTypes from 'prop-types';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import MenuItem from '@material-ui/core/MenuItem';
import Rating from './Rating';

class IdeaForm extends Component {

  constructor(props) {
    super(props);
    this.state = {idea: this.props.idea};
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if(prevProps.idea.id !== this.props.idea.id) {
      this.setState({idea: this.props.idea})
    }
  }

  handleChange = name => event => {
    this.setState({idea: {...this.state.idea, [name]: event.target.value}})
  };

  handleRatingChange = value => {
    this.setState({idea: {...this.state.idea, rating: value}})
  };

  render() {
    return (
      <div>
        <div className="title-content">
          <TextField
            className="title"
            label="Title"
            onChange={this.handleChange('name')}
            value={this.state.idea.name}
          />

          <TextField
            label="SubTitle"
            onChange={this.handleChange('subTitle')}
            value={this.state.idea.subTitle}
          />
        </div>
        <div>
          <TextField
            label="Description"
            onChange={this.handleChange('description')}
            value={this.state.idea.description}
            multiline={true}
          />
        </div>
        <div>
          <Rating value={this.state.idea.rating} max={5} onChange={this.handleRatingChange} readOnly={false} />
        </div>
        <div>
          <TextField
            select
            label="Feasibility"
            value={this.state.idea.feasibility}
            onChange={this.handleChange('feasibility')}
          >
            <MenuItem key="EASY" value="EASY">Easy</MenuItem>
            <MenuItem key="NORMAL" value="NORMAL">Normal</MenuItem>
            <MenuItem key="HARD" value="HARD">Hard</MenuItem>
            <MenuItem key="IMPOSSIBLE" value="IMPOSSIBLE">Impossible</MenuItem>
          </TextField>
        </div>
        <div>
          <Button className="save-button" variant="contained" onClick={() => this.props.saveIdeaHandler(this.state.idea, this.props.workspaceId)}>
            Sauvegarder
          </Button>
        </div>
      </div>
    );
  }
}

IdeaForm.propTypes = {
  saveIdeaHandler: PropTypes.func.isRequired,
  idea: PropTypes.shape({
    id: PropTypes.number,
    name: PropTypes.string,
    description: PropTypes.string,
    rating: PropTypes.number,
    feasibility: PropTypes.string
  }).isRequired
};

export default IdeaForm;
