import React, { Component } from 'react';
import { SHr, SImage, SLoad, SText, STheme, SView } from 'servisofts-component';
export default class index extends Component {
    static INDETIFIER = "design";
    static LABEL = "CUSTOMIZE DESIGN";
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    renderType({ key, label }) {
        if (!this.props.types) return <SLoad />
        let types = this.props.types[key];
        let tvalue = this.props.parent.state[key] ?? "Default"
        return <SView col={"xs-12"}>
            <SHr />
            <SText bold >{label}</SText>
            <SHr />
            <SView col={"xs-12"} row>
                {types.map(a => {
                    let select = tvalue == a;
                    let res = "";
                    try {
                        console.log(key, a)
                        res = require("../../../Assets/img/desing/" + key + "/" + a + ".png")
                    } catch (error) {

                    }
                    return <SView width={64} height={64} padding={4}>
                        <SView col={"xs-12"} flex card center
                            style={{
                                borderWidth: !select ? 0 : 3
                            }}
                            border={!select ? null : STheme.color.success}
                            onPress={() => {
                                this.props.parent.setState({ [key]: a })
                            }}>
                            <SText fontSize={10} col={"xs-12"}>{a}</SText>
                            <SView col={"xs-12"} height style={{
                                position: "absolute",
                                padding: 4,
                                backgroundColor: "#fff"
                            }}>
                                <SImage src={res} width={"100%"} />
                            </SView>
                        </SView>
                    </SView>
                })}
            </SView>
        </SView>
    }
    render() {
        return <SView col={"xs-12"} >
            {this.renderType({ key: "body", label: "Body Shape" })}
            {this.renderType({ key: "framework", label: "Eye Frame Shape" })}
            {this.renderType({ key: "header", label: "Eye Ball Shape" })}
            <SHr height={50}/>
        </SView>
    }
}
