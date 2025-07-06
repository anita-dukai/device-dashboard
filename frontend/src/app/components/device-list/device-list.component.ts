import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { StatusType } from 'src/app/enums/status-type';
import { Device } from 'src/app/models/device';
import { DeviceService } from 'src/app/services/device.service';
import { IP_ADDRESS_REGEX } from 'src/app/validators/ip-address.validator';

@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {
  
  devices: Device[] = [];

  showAddDeviceDialog = false;

  deviceForm: FormGroup;
  
  constructor(
    private deviceService: DeviceService,
    private fb: FormBuilder,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
    this.deviceForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      ip: ['', [Validators.required, Validators.pattern(IP_ADDRESS_REGEX)]],
      location: ['', Validators.required]
    });
   }

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
 
  openAddDevice() {
    this.showAddDeviceDialog = true;
    this.deviceForm.reset();
  }

  addDevice() {
    if (this.deviceForm.invalid) {
      this.deviceForm.markAllAsTouched();
      return;
    }

    const device: Device = this.deviceForm.value;
      
    this.deviceService.addDevice(device).subscribe({
      next: (savedDevice) => {
        this.devices.push(savedDevice);
          
        this.showAddDeviceDialog = false;
        this.deviceForm.reset();
      },
      error: (err) => {
        console.error('Error occurred while saving', err);
      }
    });
  }

  confirmDelete(deviceId: number) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this device?',
      header: 'Confirm Deletion',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteDevice(deviceId);
      }
    });
  }
  
  deleteDevice(id: number) {
    this.deviceService.deleteDevice(id)
    .subscribe({
      next: () => {
        this.loadDevices();
        this.messageService.add({
          severity: 'success',
          summary: 'Deleted',
          detail: 'Device removed successfully',
          life: 3000
        });
      },
      error: (err) => {
        console.error('Error during deletion', err);
        this.messageService.add({
          severity: 'error',
          summary: 'Delete Failed',
          detail: 'Failed to remove device',
          life: 3000
        });
      }
    });
  }

}