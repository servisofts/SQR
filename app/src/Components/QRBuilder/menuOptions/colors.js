import React, { Component } from 'react';
import { SHr, SInput, SLoad, SText, STheme, SView } from 'servisofts-component';
export default class index extends Component {
    static INDETIFIER = "colors";
    static LABEL = "SET COLORS";
    constructor(props) {
        super(props);
        this.state = {
        };
    }
    renderCB({ label, id }) {
        return <SView row center padding={4}>
            <SView width={24}>
                <SInput type='checkBox'
                    key={id}
                    value={this.props.parent.state.type_color == id}
                    onChangeText={(val) => {
                        this.props.parent.setState({ type_color: id })
                    }} />
            </SView >
            <SText fontSize={12}>{label}</SText>
            <SView width={4} />
        </SView>
    }

    getColorForeground() {
        const { state, setState } = this.props.parent;

        return <>
            <SInput value={state?.colorBody}
                type='color'
                onChangeText={val => this.props.parent.setState({ colorBody: val })} />
            <SHr />
            {state.type_color == "solid" ? null : <SInput value={state?.colorBody2}
                type='color'
                placeholder={"#000000"}
                onChangeText={val => this.props.parent.setState({ colorBody2: val })} />}
        </>
    }
    render() {
        const { state, setState } = this.props.parent;
        if (!this.props.types) return <SLoad />
        return <SView col={"xs-12"}>
            <SHr h={16} />

            <SText bold>Foreground Color</SText>
            <SHr h={16} />
            <SView row col={"xs-12"}>
                {this.renderCB({ label: "Single Color", id: "solid" })}
                <SView width={8} />
                {this.renderCB({ label: "Gra. Linear", id: "linear" })}
                <SView width={8} />
                {this.renderCB({ label: "Gra. Radial", id: "radial" })}
            </SView>
            <SHr />
            {this.getColorForeground()}
            <SHr h={16} />
            <SInput label={"Background Color"}
                type='color'
                value={state?.colorBackground}
                onChangeText={val => this.props.parent.setState({ colorBackground: val })} />
            <SHr h={50} />
        </SView>
    }
}
