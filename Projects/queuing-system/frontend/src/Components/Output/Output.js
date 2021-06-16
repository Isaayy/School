import React from 'react';

import './Output.scss';

const Output = props => {
  return (
    <div className="output">
      <p className="output__label">{props.children}</p>
      <div className="output__output" type="text">
        {props.val ? props.val.toFixed(2) : '\xa0'}
      </div>
    </div>
  );
};

export default Output;
