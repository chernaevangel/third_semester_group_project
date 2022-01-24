import React, { Component } from 'react';
import { render } from 'react-dom';
import { Switch, SwitchLabel, SwitchRadio, SwitchSelection } from './VisibilitySwitchStyle';

const titleCase = str =>
    str.split(/\s+/).map(w => w[0].toUpperCase() + w.slice(1)).join(' ');

const ClickableLabel = ({ title, onChange, id }) =>
    <SwitchLabel onClick={() => onChange(title)} className={id}>
        {titleCase(title)}
    </SwitchLabel>;

const ConcealedRadio = ({ value, selected }) =>
    <SwitchRadio type="radio" name="switch" checked={selected === value} />;

class LanguageSwitch extends Component {
    state = {
        selected: this.props.selected,
        firstSelected1: this.props.selected
    };


    handleChange = val => {
        this.setState({ selected: val });
        if(val != this.state.firstSelected)
        {
            this.props.setSubmit(true);
        }
        else{
            this.props.setSubmit(false);
        }
        this.props.setSelected(val);

    };

    count = 0;


    selectionStyle = () => {

        return {
            left: `${this.props.values.indexOf(this.state.selected) / 3 * 100}%`,
        };
    };

    render() {
        const { selected } = this.state;
        return (
            <Switch>
                {this.props.values.map(val => {
                    return (
                        <span>
              <ConcealedRadio value={val} selected={selected} />
              <ClickableLabel title={val} onChange={this.handleChange} />
            </span>
                    );
                })}
                <SwitchSelection className="switch-selection" style={this.selectionStyle()}  />
            </Switch>
        );
    }
}
export default LanguageSwitch