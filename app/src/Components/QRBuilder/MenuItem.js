import React, { Component } from 'react';
import { SIcon, SText, STheme, SView } from 'servisofts-component';

export default class MenuItem extends Component {
    constructor(props) {
        super(props);
        this.state = {

        };
    }
    handlePress = () => {
        if (this.select) {
            this.props.parent.setState({ select: "" })
        } else {
            this.props.parent.setState({ select: this.props.indetifier })
        }

    }

    renderContent() {
        if (!this.select) return null;
        return <SView col={"xs-12"}>
            {this.props.children}
        </SView>
    }
    render() {
        const { indetifier, label, parent } = this.props;
        this.select = parent?.state?.select == indetifier;
        return <>
            <SView col={"xs-12"} height={50} borderRadius={4} row
                style={{
                    overflow: "hidden"
                }}
                backgroundColor={!this.select ? STheme.color.background : STheme.color.success + "22"}
                // border={STheme.color.background}
                onPress={this.handlePress.bind(this)}>
                <SView width={50} height backgroundColor={!this.select ? STheme.color.warning + 44 : STheme.color.success} padding={16}>
                    <SIcon name='World' fill={!this.select ? STheme.color.gray : STheme.color.secondary} />
                </SView>
                <SView width={16} />
                <SView flex height center>
                    <SText bold fontSize={16} col={"xs-12"}>{label}</SText>
                </SView>
                <SView width={50} height padding={16}>
                    <SIcon name={!this.select ? "Menu" : "Close"} fill={STheme.color.gray} />
                </SView>
            </SView>
            {this.renderContent()}
        </>
    }
}
