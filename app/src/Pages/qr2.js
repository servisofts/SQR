import React, { Component } from 'react';
import { SHr, SPage, STheme, SView, } from 'servisofts-component';
import QRBuilder from '../Components/QRBuilder';
export default class index extends Component {
    constructor(props) {
        super(props);
        this.state = {

        };
    }
    render() {
        return <SPage>
            <SView col={"xs-12"} center>
                <SView col={"xs-12 md-10 xl-6"}>
                    <QRBuilder />
                </SView>
            </SView>
            {/* <SHr /> */}
            {/* <SHr h={60} color={STheme.color.card} /> */}
        </SPage>
    }
}
