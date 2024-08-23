import React, { Component } from 'react';
import { SHr, SInput, SLoad, SText, STheme, SView } from 'servisofts-component';
export default class index extends Component {
    static INDETIFIER = "detail";
    static LABEL = "FRAMEWORK";
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        const { state, setState } = this.props.parent;
        if (!this.props.types) return <SLoad />
        return <SView col={"xs-12"} >
            <SHr h={16} />
            <SInput label={"Type"}
                col={"xs-5.5"}
                value={state?.detail}
                type='select'
                options={this.props.types.detail}
                onChangeText={val => this.props.parent.setState({ detail: val })} />
            <SHr h={16} />
            <SHr h={50} />
        </SView>
    }
}
