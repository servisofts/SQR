import React from 'react';
import { SComponentContainer, SNavigation } from 'servisofts-component';
import SSocket, { setProps } from 'servisofts-socket';
import Redux, { store } from './Redux';
import Config from "./Config";
import Assets from './Assets';
import Pages from './Pages';
import BackgroundImage from './Components/BackgroundImage';

setProps(Config.socket);


const App = (props) => {
    return (
        <Redux>
            <SComponentContainer
                debug
                socket={SSocket}
                assets={Assets}
                inputs={Config.inputs}
                background={<BackgroundImage />}
                theme={{ initialTheme: "dark", themes: Config.theme }}>
                <SNavigation props={{
                    prefixes: ["https://component.servisofts.com", "component.servisofts://"],
                    pages: Pages,
                    title: "SS-SQR"
                }} />
                <SSocket
                    store={store}
                    identificarse={(props) => {
                        var usuario = props?.state?.usuarioReducer?.usuarioLog;
                        return {
                            data: usuario ? usuario : {},
                            deviceKey: "as-asa-as",
                        }
                    }} />
            </SComponentContainer>
        </Redux>
    )
}
export default App;