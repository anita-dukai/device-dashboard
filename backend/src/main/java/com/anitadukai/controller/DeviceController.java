package com.anitadukai.controller;

import com.anitadukai.request.DeviceRequest;
import com.anitadukai.response.DeviceResponse;
import com.anitadukai.response.StatusHistoryResponse;
import com.anitadukai.service.DeviceService;
import com.anitadukai.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final StatusHistoryService statusHistoryService;

    @GetMapping
    public List<DeviceResponse> getDevices() {
        return deviceService.getAllDevices().stream()
                .map(DeviceResponse::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DeviceResponse createDevice(@RequestBody final DeviceRequest request) {
        return DeviceResponse.from(deviceService.createDevice(request));
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable final Long id) {
        deviceService.deleteDeviceById(id);
    }

    @GetMapping("/{id}/status-history")
    public List<StatusHistoryResponse> getStatusHistories(@PathVariable final Long id) {
        return statusHistoryService.getStatusHistories(id).stream()
                .map(StatusHistoryResponse::from)
                .collect(Collectors.toList());
    }

}