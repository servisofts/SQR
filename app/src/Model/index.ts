import { SModel } from "servisofts-model";
import servicio from "./servicio";

const Model = {
    ...servicio
}
export default {
    ...Model,
    ...SModel.declare(Model)
}