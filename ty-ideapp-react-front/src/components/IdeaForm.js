import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

class IdeaForm extends Component {

  handleChange = name => event => {
    this.setState({idea: {...this.state.idea, [name]: event.target.value}})
  };

  render() {
    return (
      <div>
        <div className="title-content">
          <TextField
            label="Title"
            onChange={this.handleChange('name')}
            value={this.props.idea.name}
          />

          <TextField
            label="SubTitle"
            onChange={this.handleChange('subTitle')}
            value={this.props.idea.subTitle}
          />

          <TextField
            label="Description"
            onChange={this.handleChange('description')}
            value={this.props.idea.description}
            multiline={true}
          />

        </div>
        <div>
          <Button variant="contained" onClick={() => this.props.saveIdeaHandler(this.props.idea, this.props.workspaceId)}>
            Sauvegarder
          </Button>
        </div>
      </div>
    );
  }
}

export default IdeaForm;
