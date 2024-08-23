import React, { Component } from 'react';
import { SHr, SInput, SLoad, SText, STheme, SView } from 'servisofts-component';
export default class index extends Component {
    static INDETIFIER = "logo";
    static LABEL = "ADD LOGO IMAGE";
    constructor(props) {
        super(props);
        this.state = {
            type: "url"
        };
    }
    renderCB({ label, id, render }) {
        if (this.state.type == id) {
            this.render_select = render;
        }
        return <SView row center padding={4}>
            <SView width={24}>
                <SInput type='checkBox'
                    key={id}
                    value={this.state.type == id}
                    onChangeText={(val) => {
                        this.setState({ type: id })
                    }} />
            </SView >
            <SText fontSize={12}>{label}</SText>
            <SView width={4} />
        </SView>
    }
    render_opt() {
        if (!this.render_select) return null;
        return this.render_select();

    }

    render() {
        const { state, setState } = this.props.parent;
        if (!this.props.types) return <SLoad />
        return <SView col={"xs-12"} >
            <SHr h={16} />
            <SText bold>Image</SText>
            <SHr h={16} />
            <SView row col={"xs-12"}>
                {this.renderCB({
                    label: "URL", id: "url", render: () => {
                        return <SInput label={"URL Image"}
                            value={state?.image_src}
                            placeholder={"https://servisofts.com/logo.png"}
                            onChangeText={val => this.props.parent.setState({ image_src: val, image: "" })} />
                    }
                })}
                <SView width={8} />
                {this.renderCB({
                    label: "Base64", id: "b64", render: () => {
                        return <SInput label={"Image in base64"}
                            type='textArea'
                            value={state?.image}
                            placeholder={"b64 code"}
                            onChangeText={val => this.props.parent.setState({ image: val, image_src: "" })} />
                    }
                })}
            </SView>
            <SHr />
            {this.render_opt()}

            {/* <SInput label={"Background Color"}
                icon={<SView width={40} height backgroundColor={state.colorBackground}></SView>}
                value={state?.colorBackground}
                onChangeText={val => this.props.parent.setState({ colorBackground: val })} /> */}
            <SHr h={50} />
        </SView>
    }
}
