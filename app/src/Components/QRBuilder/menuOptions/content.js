import React, { Component } from 'react';
import { SHr, SInput, SLoad, SText, STheme, SView } from 'servisofts-component';
export default class index extends Component {
    static INDETIFIER = "content";
    static LABEL = "ENTER CONTENT";
    constructor(props) {
        super(props);
        this.state = {
        };
    }


    render() {
        const { state, setState } = this.props.parent;
        if (!this.props.types) return <SLoad />
        return <SView col={"xs-12"} >
            <SView row col={"xs-12"} style={{
                justifyContent: "space-between"
            }}>
                <SInput label={"Type"}
                    col={"xs-5.5"}
                    value={state?.content_type}
                    type='select'
                    options={this.props.types.content_type}
                    onChangeText={val => this.props.parent.setState({ content_type: val })} />
                <SInput label={"Correction lvl"}
                    col={"xs-3.5"}
                    style={{
                        textAlign: "center"
                    }}
                    value={state?.errorCorrectionLevel}
                    type='select'
                    options={this.props.types.errorCorrectionLevel}
                    onChangeText={val => this.props.parent.setState({ errorCorrectionLevel: val })} />
                <SInput label={"You " + state?.content_type}
                    value={state?.content}
                    onChangeText={val => this.props.parent.setState({ content: val })} />
                <SHr h={16} />
            </SView>
        </SView>
    }
}
