import { SPageListProps } from 'servisofts-component'
const ServiceName = "sqr";

import qr from './Components/qr';
const Pages: SPageListProps = {
    ...qr.Pages
}

const Reducers = {
    ...qr.Reducers
}

export default {
    ServiceName,
    Pages,
    Reducers

};

