import React, { Component } from 'react';
import { SHr, SImage, SPopup, SText, STheme, SView } from 'servisofts-component';
import SSocket from 'servisofts-socket'
export default class Menu extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    componentDidMount() {
        this.create_qr();
    }

    create_qr() {
        const dataSend = {
            "component": "qr",
            "type": "registro",
            "estado": "cargando",
            "data": this.props.parent.state
        }
        SSocket.sendPromise(dataSend).then((resp) => {
            this.setState({ loading: false })
            if (resp.estado == "exito") {
                this.setState({ data: resp.data })
            }
        }).catch((e) => {
            console.error(e)
            SPopup.alert(e.error)
            this.setState({ loading: false })
        })
    }
    renderOptions() {
        return <SView col={"xs-12"} row center>
            <SView padding={16} card onPress={this.create_qr.bind(this)} style={{
                backgroundColor: STheme.color.success
            }}><SText color={STheme.color.white}>Create QR Code</SText></SView>
            {/* <SView width={8} /> */}
            {/* <SView padding={16} card><SText>Dowload PNG</SText></SView> */}
        </SView>
    }

    render() {
        return <SView col={"xs-12"}  >
            <SView col={"xs-12"} center >
                <SView col={"xs-12"} center style={{
                    maxWidth: 300
                }}>
                    <SView col={"xs-12"} colSquare padding={16}>
                        <SImage enablePreview src={`data:image/png;base64,${this.state?.data?.b64}`} style={{
                            width: "100%",
                            height: "100%"
                        }} />
                    </SView>
                    <SView center padding={4} card margin={8} style={{
                        paddingRight: 20,
                        paddingLeft: 20,
                        backgroundColor: STheme.color.warning
                    }}>
                        <SText fontSize={12} color={STheme.color.white}>Warning We recommend to give your colors more contrast between back- and foreground to work with all QR code readers.</SText>
                    </SView>
                    {this.renderOptions()}

                </SView>
            </SView>
            {/* <SText>{JSON.stringify(this.props.parent.state, "\n", "\t")}</SText> */}
        </SView>
    }
}
