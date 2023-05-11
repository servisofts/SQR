import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SForm, SHr, SIcon, SNavigation, SPage, SText, SView, SLoad, SButtom, SImage, SInput, STheme, SStorage, SThread } from 'servisofts-component';
import Parent from '../index';
import SSocket from 'servisofts-socket';
class Registro extends Component {
    constructor(props) {
        super(props);
        this.state = {
            opt: {
                content: "http://servisofts.com",
                type_color: "solid",
                colorBackground:"#ffffff",
                colorBody: "#000000"
            }
        };
    }
    componentDidMount() {
        SStorage.getItem("lastqr", e => {
            try {
                let data = JSON.parse(e)
                if (data) {
                    this.state.opt = data;
                }
            } catch (e) {
                console.log("El objeto no es JSON")
            }

            this.pedirQR();
            this.setState({ loading_data: true })
        })
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
    pedirQR(intent = 0) {
        if (intent > 3) {
            return;
        }
        this.setState({ loading: true })
        SStorage.setItem("lastqr", JSON.stringify(this.state.opt ?? {}));
        const dataSend = {
            "component": "qr",
            "type": "registro",
            "estado": "cargando",
            "data": this.state.opt
        }
        // new SThread(1000, "sadasd", false).start(() => {
        SSocket.sendPromise(dataSend).then((resp) => {
            this.setState({ loading: false })
            if (resp.estado == "exito") {
                this.setState({ data: resp.data })
            }
        }).catch((e) => {
            this.setState({ loading: false })
            this.pedirQR(intent + 1);
        })
        // })

    }
    renderForm() {
        if (!this.state.loading_data) return <SLoad />
        if (!this.state.types) return <SLoad />
        // content,width,height,body,header,framework,colorBackground,colorBody,colorHeader,colorImageBackground,image,image_src
        return <SForm
            row
            style={{
                justifyContent: "space-between"
            }}
            inputProps={{
                col: "xs-5.5"
            }}
            inputs={{
                "content": { label: "URL", required: true, defaultValue: this.state?.opt?.content, type: "text", col: "xs-12" },
                "colorBackground": { label: "colorBackground", defaultValue: this.state?.opt?.colorBackground, type: "text" },
                "type_color": { label: "Type color", defaultValue: this.state?.opt?.type_color, type: "select", options: ["solid", "linear", "radial"] },
                "colorBody": { label: "colorBody", defaultValue: this.state?.opt?.colorBody, type: "text" },
                "colorBody2": { label: "colorBody2", defaultValue: this.state?.opt?.colorBody2, type: "text" },
                "colorHeader": { label: "colorHeader", defaultValue: this.state?.opt?.colorHeader, type: "text" },
                "colorImageBackground": { label: "colorImageBackground", defaultValue: this.state?.opt?.colorImageBackground, type: "text" },
                "image_src": { label: "image_src", defaultValue: this.state?.opt?.image_src, type: "text", col: "xs-12" },
                "image": { label: "image b64", defaultValue: this.state?.opt?.image, type: "textArea", col: "xs-12" },
                "body": { label: "Body", defaultValue: this.state?.opt?.body, type: "select", options: this.state.types.body },
                "framework": { label: "framework", defaultValue: this.state?.opt?.framework, type: "select", options: this.state.types.framework },
                "header": { label: "header", defaultValue: this.state?.opt?.header, type: "select", options: this.state.types.header },


            }}
            onSubmitName={"GENERAR QR"}
            onSubmit={(values) => {
                this.state.opt = values;
                this.pedirQR();
            }}
        />
    }

    getImage() {
        if (!this.state.data) return <SLoad />;
        if (this.state.loading) return <SLoad />;
        return <SImage src={`data:image/png;base64,${this.state.data.b64}`} style={{
            width: "100%",
            height: "100%"
        }} />
    }
    render() {
        console.log(this.state);
        return (
            <SPage title={'Registro de ' + Parent.component} center>
                <SView col={"xs-12"} row >
                    <SView col={"xs-12 md-6"} center  >
                        <SView col={"xs-11"}>
                            {this.renderForm()}

                        </SView>
                    </SView>
                    <SView col={"xs-12 md-6"} center padding={8}>
                        <SHr h={16} />
                        <SView col={"xs-11"} colSquare center style={{
                            maxWidth: 300
                        }} >
                            {this.getImage()}
                            {/* <SView col={"xs-12"} height style={{
                                position: "absolute"
                            }} row>
                                <SView col={"xs-6"} colSquare border={"#00f"}></SView>
                                <SView col={"xs-6"} colSquare border={"#00f"}></SView>
                                <SView col={"xs-6"} colSquare border={"#00f"}></SView>
                                <SView col={"xs-6"} colSquare border={"#00f"}></SView>
                            </SView> */}
                        </SView>

                    </SView>
                    <SView col={"xs-12 md-6"} center>
                        <SHr h={50} />
                        <SView col={"xs-11"} center >
                            <SText>Copia las propiedades del qr: </SText>
                            <SView col={"xs-12"} border={STheme.color.card} >
                                <SInput type='textArea' value={JSON.stringify(this.state.opt, "\n", "\t")} height={400} />
                                {/* <SText>{JSON.stringify(this.state.opt, "\n", "\t")}</SText> */}
                            </SView>
                        </SView>
                    </SView>
                </SView>
                <SHr h={100} />
            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(Registro);