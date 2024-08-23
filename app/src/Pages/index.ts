import { SPage, SPageListProps } from 'servisofts-component';

import root from "./root";
import qr from './qr';
import qr2 from './qr2';

export default SPage.combinePages("/", {
    "": root,
    qr,
    qr2,
});


