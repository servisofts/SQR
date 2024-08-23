import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SColorPicker, SHr, SIcon, SNavigation, SPage, SText, SView } from 'servisofts-component';

class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    renderBTN({ label, url }) {

        return <SView onPress={() => SNavigation.navigate(url)} card padding={16}><SText>{label}</SText></SView>
    }

    render() {
        return (
            <SPage hidden center disableScroll>
                <SView center card padding={16} margin={16}>
                    <SText bold fontSize={50} center>{'SQR'}</SText>
                    <SHr h={32} />
                    <SText bold fontSize={20} center>{'Servisofts QR generator.'}</SText>
                    <SHr />
                    <SText center>{'Servisofts QR permite crear codigos QR con diferentes estilos, colores y contenidos.'}</SText>
                    <SHr h={32} />
                    {this.renderBTN({ label: "QR 0", url: "/qr" })}
                    <SHr />
                    {this.renderBTN({ label: "QR 2", url: "/qr2" })}
                    <SHr />
                </SView>

            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(index);