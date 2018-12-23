import React, { Component } from 'react';
import StarIcon from '@material-ui/icons/Star';
import IconButton from '@material-ui/core/IconButton';

const starValidatedStyle = {
  color: "yellow"
};

const starDefaultStyle = {
  color: "white"
};

class Rating extends Component {

  render() {
    let stars = [];
    for (let i = 0; i < this.props.max; i++) {
      let style;
      if(i < this.props.value){
        style = starValidatedStyle;
      } else {
        style = starDefaultStyle;
      }
      stars.push(<IconButton key={i+1} aria-label={i+1} onClick={() => this.props.onChange(i+1)} disabled={this.props.readOnly}>
                  <StarIcon style={style} />
                </IconButton>);
    }

    return (
      <div>
        {stars}
      </div>
    );
  }
}

export default Rating;
