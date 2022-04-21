import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SForm, SHr, SIcon, SNavigation, SPage, SText, SView, SLoad, SButtom, SImage, SInput } from 'servisofts-component';
import Parent from '../index';
import SSocket from 'servisofts-socket';
const B64 = `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`
class Registro extends Component {
    constructor(props) {
        super(props);
        this.state = {
            select: {
                body: "Default",
                header: "Default",
                framework: "Default",
            },
            colorBackground: "#ffffff",
            colorBody: "#000000",
            colorHeader: "#000000",
            colorImageBackground: "",
            content: "https://servisofts.com"
        };
    }

    componentDidMount() {
        this.pedirTypes();
    }
    async pedirTypes() {
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
    getItem(item, type) {
        var isSelect = false;
        if (this.state.select[type] == item) {
            isSelect = true;
        }
        return <>
            <SView style={{
                height: 50,
                width: 140,
            }} >
                <SView style={{
                    borderWidth: 1,
                    height: "94%",
                    width: "94%",
                    overflow: 'hidden',
                }} center onPress={() => {
                    this.setState({
                        select: {
                            ...this.state.select,
                            [type]: item
                        }
                    })
                }}>
                    <SText center color={isSelect ? "#fff" : "#666"}>{item}</SText>
                </SView>
            </SView>
        </>
    }
    getTypes() {
        if (!this.state.types) return null;
        return <SView col={"xs-12"}>
            <SHr />
            <SView col={"xs-12"}>
                <SText>Body</SText>
                <SView col={"xs-12"} row>
                    {this.state.types["body"].map((item, index) => this.getItem(item, "body"))}
                </SView>
            </SView>
            <SHr />
            <SView col={"xs-12"}>
                <SText>Header</SText>
                <SView col={"xs-12"} row>
                    {this.state.types["header"].map((item, index) => this.getItem(item, "header"))}

                </SView>
            </SView>
            <SHr />
            <SView col={"xs-12"}>
                <SText>Framework</SText>
                <SView col={"xs-12"} row>
                    {this.state.types["framework"].map((item, index) => this.getItem(item, "framework"))}

                </SView>
            </SView>
        </SView>
    }
    getImage() {
        if (!this.state.data) return null;
        return <SImage src={`data:image/png;base64,${this.state.data.b64}`} style={{
            width: 512,
            height: 512
        }} />
    }
    getInputs() {
        return (
            <SView col={"xs-11 sm-10 md-8 lg-6 xl-4"} row>
                <SInput onChangeText={(val) => { this.setState({ content: val }) }} value={this.state.content} label={"content"} customStyle={"calistenia"} />
                <SInput onChangeText={(val) => { this.setState({ colorBackground: val }) }} value={this.state.colorBackground} label={"colorBackground"} customStyle={"calistenia"} />
                <SInput onChangeText={(val) => { this.setState({ colorBody: val }) }} value={this.state.colorBody} label={"colorBody"} customStyle={"calistenia"} />
                <SInput onChangeText={(val) => { this.setState({ colorHeader: val }) }} value={this.state.colorHeader} label={"colorHeader"} customStyle={"calistenia"} />
                <SInput onChangeText={(val) => { this.setState({ colorImageBackground: val }) }} value={this.state.colorImageBackground} label={"colorImageBackground"} customStyle={"calistenia"} />
            </SView>
        )
    }
    render() {

        return (
            <SPage title={'Registro de ' + Parent.component} center>
                <SView row>
                    <SView col={"xs-12 md-6"} center>
                        <SHr />
                        {this.getInputs()}
                        {this.getTypes()}
                        <SHr />
                        <SButtom type={"danger"} onPress={() => {
                            SSocket.sendPromise({
                                "component": "qr",
                                "type": "registro",
                                "estado": "cargando",
                                "data": {
                                    "content": this.state.content,
                                    "body": this.state.select["body"],
                                    "header": this.state.select["header"],
                                    "framework": this.state.select["framework"],
                                    "colorBackground": this.state.colorBackground,
                                    "colorBody": this.state.colorBody,
                                    "colorHeader": this.state.colorHeader,
                                    "image": B64,
                                    ...(!this.state.colorImageBackground ? {} : { "colorImageBackground": this.state.colorImageBackground, })

                                }
                            }).then((resp) => {
                                if (resp.estado == "exito") {
                                    this.setState({ data: resp.data })
                                }
                            });
                        }}>CREAR</SButtom>
                        <SHr />
                    </SView>
                    <SView col={"xs-12 md-6"} center>
                        {this.getImage()}
                    </SView>
                </SView>

            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(Registro);