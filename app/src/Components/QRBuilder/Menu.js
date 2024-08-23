import React, { Component } from 'react';
import { SHr, SText, STheme, SView } from 'servisofts-component';
import SSocket from 'servisofts-socket';

import MenuItem from './MenuItem';
import content from "./menuOptions/content"
import colors from "./menuOptions/colors"
import logo from "./menuOptions/logo"
import design from "./menuOptions/design"
import detail from "./menuOptions/detail"
const Options = {
    content,
    colors,
    logo,
    design,
    detail
}
export default class Menu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            select: "content"
        };
    }

    componentDidMount() {
        this.pedirTypes();
    }
    async pedirTypes() {
        console.log("Pidiendo types")
        SSocket.sendPromise({
            "component": "qr",
            "type": "getTypes",
            "estado": "cargando"
        }).then((resp) => {
            if (resp.estado == "exito") {
                this.setState({ types: resp.data })
            }
        }).catch((err) => {
            this.pedirTypes();
        })
    }
    render() {
        return <SView col={"xs-12"} padding={16}>
            {Object.values(Options).map(ELM => {
                return <>
                    <MenuItem parent={this} indetifier={ELM.INDETIFIER} label={ELM.LABEL} >
                        <ELM {...this.props} types={this.state.types} />
                    </MenuItem>
                    <SHr h={4} />
                </>
            })}
        </SView>
    }
}
