import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SForm, SHr, SIcon, SNavigation, SPage, SText, SView, SLoad, SButtom, SImage, SInput, STheme, SStorage, SThread } from 'servisofts-component';
import SSocket from 'servisofts-socket';
class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
            opt: {
                content: "http://servisofts.com",
                type_color: "solid",
                colorBackground: "#ffffff",
                colorBody: "#000000",

            },
            bgc: STheme.color.background
        };
    }
    componentDidMount() {
        this.pedirTypes();
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
        // if (this.state.loading) return;

        this.setState({ loading: true })
        this.setValueTextArea();
        SStorage.setItem("lastqr", JSON.stringify(this.state.opt ?? {}));
        const dataSend = {
            "component": "qr",
            "type": "registro",
            "estado": "cargando",
            "data": this.state.opt
        }
        console.log("[OUT] Creando QR", dataSend)

        // new SThread(1000, "sadasd", false).start(() => {
        SSocket.sendPromise(dataSend).then((resp) => {
            this.setState({ loading: false })
            console.log("[IN] QR Creado con exito", resp)
            if (resp.estado == "exito") {
                this.setState({ data: resp.data })
            }
        }).catch((e) => {
            console.log("[IN] Error al crear QR")
            console.error(e)
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
            ref={(ref) => this.form = ref}
            row
            style={{
                justifyContent: "space-between"
            }}
            inputProps={{
                col: "xs-5.5"
            }}
            inputs={{
                "content_type": { label: "Content type", required: true, defaultValue: this.state?.opt?.content_type, type: "select", options: this.state.types.content_type, col: "xs-5" },
                "errorCorrectionLevel": { label: "errorCorrectionLevel", required: true, defaultValue: this.state?.opt?.errorCorrectionLevel ?? "H", type: "select", options: this.state.types.errorCorrectionLevel, col: "xs-5" },
                "content": { label: "Contenido", required: true, defaultValue: this.state?.opt?.content, type: "textArea", col: "xs-12" },
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
                "detail": { label: "detail", defaultValue: this.state?.opt?.detail, type: "select", options: this.state.types.detail },
            }}
            onSubmit={(values) => {
                this.state.opt = values;
                this.form_detail.submit();
                this.pedirQR();
            }}
        />
    }
    renderFormDetail() {
        if (!this.state.loading_data) return <SLoad />
        if (!this.state.types) return <SLoad />
        // content,width,height,body,header,framework,colorBackground,colorBody,colorHeader,colorImageBackground,image,image_src
        let detail_data = this.state?.opt?.detail_data ?? {};
        return <SForm
            ref={(ref) => this.form_detail = ref}
            row
            style={{
                justifyContent: "space-between"
            }}
            inputProps={{
                col: "xs-5.5"
            }}
            inputs={{
                "width": {
                    label: "Width",
                    required: true,
                    type: "number",
                    defaultValue: detail_data?.width ?? 1024,
                },
                "height": {
                    label: "height",
                    required: true,
                    type: "number",
                    defaultValue: detail_data?.height ?? 1024,
                },
                "background": {
                    label: "Background",
                    required: true,
                    defaultValue: detail_data?.background ?? "#ffffff",
                },
            }}
            onSubmit={(values) => {
                this.state.opt.detail_data = values;
            }}
        />
    }
    getImage() {
        if (!this.state.data) return <SLoad />;
        if (this.state.loading) return <SLoad />;
        return <SImage enablePreview src={`data:image/png;base64,${this.state.data.b64}`} style={{
            width: "100%",
            height: "100%"
        }} />
    }

    handlePress() {
        // this.form_detail.submit();
        this.form.submit();
    }

    setValueTextArea() {
        if (this.textarea) {
            this.textarea.setValue(JSON.stringify(this.state.opt, "\n", "\t"))
        }

    }
    render() {
        return (
            <SPage title={'Registro de '} center>
                < SView col={"xs-12"} row >
                    <SView col={"xs-12 md-6"} center  >
                        <SView col={"xs-11"} center>
                            <SHr />
                            <SText bold>Ajustes del codigo QR</SText>
                            {this.renderForm()}
                            <SHr />

                            <SText bold>Ajustes de la imagen</SText>
                            {this.renderFormDetail()}
                            <SHr />

                        </SView>
                    </SView>
                    <SView col={"xs-12 md-6"} padding={8} borderRadius={8} card>

                        <SView col={"xs-12"} center>
                            <SHr />
                            <SView col={"xs-11"} colSquare center style={{
                                maxWidth: 600,
                                backgroundColor: this.state.bgc ?? STheme.color.background
                            }} card>
                                {this.getImage()}
                            </SView>
                            <SHr h={16} />
                            <SView card padding={16} onPress={this.handlePress.bind(this)} style={{
                                backgroundColor: '#ff0000'
                            }}>
                                <SText>GENERAR QR</SText>
                            </SView>
                            <SHr h={16} />
                            <SView width={150}>
                                <SInput label={"Fondo de pruebas:"} value={this.state.bgc} onChangeText={(v) => { this.setState({ bgc: v }) }} />
                            </SView>
                        </SView>



                    </SView>
                    <SView col={"xs-12"} center>
                        <SHr h={50} />
                        <SView col={"xs-11"} center >
                            <SText>Copia las propiedades del qr: </SText>
                            <SView col={"xs-12"} border={STheme.color.card} >
                                <SInput ref={ref => this.textarea = ref} type='textArea' style={{}} height={400} onChangeText={(val) => {
                                    try {
                                        let json = JSON.parse(val);
                                        console.log(json);
                                        this.setState({ opt: json })
                                    } catch (e) {
                                        console.log(e);
                                    }
                                }} />
                                {/* <SText>{JSON.stringify(this.state.opt, "\n", "\t")}</SText> */}
                            </SView>
                            <SView card padding={16} onPress={() => {
                                this.pedirQR();
                            }} style={{
                                backgroundColor: '#ff0000'
                            }}>
                                <SText>GENERAR QR JSON</SText>
                            </SView>
                        </SView>
                        <SHr h={50} />
                    </SView>
                </SView>
            </SPage >
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(index);