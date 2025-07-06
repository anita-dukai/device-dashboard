import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DeviceListComponent } from './components/device-list/device-list.component';

import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DialogModule } from 'primeng/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { MessageModule } from 'primeng/message';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DeviceListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    TagModule,
    ButtonModule,
    InputTextModule,
    BrowserAnimationsModule,
    DialogModule,
    ReactiveFormsModule,
    MessageModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
