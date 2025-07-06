package com.anitadukai.service;

import com.anitadukai.model.Device;
import com.anitadukai.repository.DeviceRepository;
import com.anitadukai.request.DeviceRequest;
import com.anitadukai.type.StatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional(readOnly = true)
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Transactional
    public Device createDevice(final DeviceRequest request) {
        final Device newDevice = new Device()
                .setName(request.name())
                .setType(request.type())
                .setIp(request.ip())
                .setStatus(StatusType.INACTIVE)
                .setLocation(request.location());

        final var savedDevice = deviceRepository.save(newDevice);

        log.info("Created new device with id {}", savedDevice.getId());
        return savedDevice;
    }

    @Transactional
    public void deleteDeviceById(final Long id) {
        final Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id " + id));

        deviceRepository.delete(device);
        log.info("Deleted device with id {}", id);
    }

}