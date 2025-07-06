import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DeviceListComponent } from './components/device-list/device-list.component';

import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DeviceListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    TagModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
