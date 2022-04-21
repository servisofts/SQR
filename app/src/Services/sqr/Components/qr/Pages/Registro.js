import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SForm, SHr, SIcon, SNavigation, SPage, SText, SView, SLoad, SButtom, SImage, SInput } from 'servisofts-component';
import Parent from '../index';
import SSocket from 'servisofts-socket';
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
                <SInput type="textArea" onChangeText={(val) => { this.setState({ image: val }) }} value={this.state.image} label={"image B64"} customStyle={"calistenia"} />
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
                                    "image": this.state.image,
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