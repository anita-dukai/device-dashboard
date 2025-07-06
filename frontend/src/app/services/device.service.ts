import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";
import { Injectable } from "@angular/core";
import { Device } from "../models/device";
import { StatusHistory } from "../models/status-history";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private deviceUrl = `${environment.apiUrl}/devices`;

  constructor(
    private http: HttpClient
  ) { }

  getAllDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.deviceUrl);
  }

  addDevice(device: Device): Observable<Device> {
    return this.http.post<Device>(this.deviceUrl, device);
  }

  deleteDevice(id: number): Observable<void> {
    return this.http.delete<void>(`${this.deviceUrl}/${id}`);
  }

  getStatusHistory(deviceId: number): Observable<StatusHistory[]> {
    return this.http.get<StatusHistory[]>(`${this.deviceUrl}/${deviceId}/status-history`);
  }

}
