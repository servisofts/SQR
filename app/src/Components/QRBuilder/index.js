import React, { Component } from 'react';
import { SHr, SText, STheme, SView } from 'servisofts-component';
import Menu from "./Menu";
import QRPanel from "./QRPanel"
export default class QRBuilder extends Component {
    constructor(props) {
        super(props);
        this.state = {
            content: "http://servisofts.com",
            content_type: "text",
            errorCorrectionLevel: "L",

            colorBackground: "#ffffff",
            type_color: "solid",
            colorBody: "#000000",

        };
    }
    render() {
        return <SView col={"xs-12"} row>
            <SView col={"xs-12 md-7"} backgroundColor={STheme.color.card} style={{
                // minHeight:500
            }}>
                <Menu parent={this} />
            </SView>
            <SView col={"xs-12 md-5"} >
                <QRPanel parent={this} />
            </SView>
            <SHr />
            {/* <SView col={"xs-12"} card padding={16}>
                <SText>{JSON.stringify(this.state, "\n", "\t")}</SText>
            </SView>     */}
        </SView>
    }
}
