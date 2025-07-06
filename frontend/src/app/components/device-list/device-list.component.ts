import { Component, OnInit } from '@angular/core';
import { StatusType } from 'src/app/enums/status-type';
import { Device } from 'src/app/models/device';
import { DeviceService } from 'src/app/services/device.service';

@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {
  
  devices: Device[] = [];
  
  constructor(
    private deviceService: DeviceService
  ) { }

  ngOnInit(): void {
    this.loadDevices();
  }

  loadDevices() {
    this.deviceService.getAllDevices()
      .subscribe(devices => this.devices = devices)
  }
  
  getSeverity(status: string): string {
    switch(status) {
      case StatusType.ACTIVE: return 'success';
      case StatusType.ERROR: return 'warning';
      case StatusType.INACTIVE: return 'info';
      default: return 'info';
    }
  }
 
}