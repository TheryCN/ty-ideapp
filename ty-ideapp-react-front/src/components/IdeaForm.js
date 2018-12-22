import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

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
