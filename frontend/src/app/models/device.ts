import { StatusType } from "../enums/status-type";

export interface Device {
    id?: number;
    name: string;
    type: string;
    ip: string;
    status: StatusType
    location: string;
}
