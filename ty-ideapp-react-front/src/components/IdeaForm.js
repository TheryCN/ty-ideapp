import React, { Component } from 'react';
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
            label="Title"
            onChange={this.handleChange('name')}
            value={this.state.idea.name}
          />

          <TextField
            label="SubTitle"
            onChange={this.handleChange('subTitle')}
            value={this.state.idea.subTitle}
          />

          <TextField
            label="Description"
            onChange={this.handleChange('description')}
            value={this.state.idea.description}
            multiline={true}
          />

          <Rating value={this.state.idea.rating} max={5} onChange={this.handleRatingChange} readOnly={false} />

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
          <Button variant="contained" onClick={() => this.props.saveIdeaHandler(this.state.idea, this.props.workspaceId)}>
            Sauvegarder
          </Button>
        </div>
      </div>
    );
  }
}

export default IdeaForm;
