import { StatusType } from "../enums/status-type";

export interface StatusHistory {
  id?: number;
  status: StatusType;
  changedAt: Date;
}