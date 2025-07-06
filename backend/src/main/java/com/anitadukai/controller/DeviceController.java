package com.anitadukai.controller;

import com.anitadukai.request.DeviceRequest;
import com.anitadukai.response.DeviceResponse;
import com.anitadukai.service.DeviceService;
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

}