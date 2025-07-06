package com.anitadukai.service;

import com.anitadukai.model.Device;
import com.anitadukai.repository.DeviceRepository;
import com.anitadukai.request.DeviceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    public void getAllDevices() {
        // given
        final Device device1 = new Device().setId(1L).setName("Device 1");
        final Device device2 = new Device().setId(2L).setName("Device 2");

        when(deviceRepository.findAll()).thenReturn(List.of(device1, device2));

        // when
        final var result = deviceService.getAllDevices();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Device 1", result.get(0).getName());
        assertEquals("Device 2", result.get(1).getName());
    }

    @Test
    public void createDevice() {
        // given
        final String name = "Test Device";
        final DeviceRequest request = DeviceRequest.builder()
                .name(name)
                .build();
        final Device savedDevice = new Device()
                .setId(1L)
                .setName(name);

        when(deviceRepository.save(any(Device.class))).thenReturn(savedDevice);

        // when
        final var result = deviceService.createDevice(request);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    public void deleteDeviceById() {
        // given
        final Long deviceId = 1L;
        final Device device = new Device().setId(deviceId);

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        // when
        deviceService.deleteDeviceById(deviceId);

        // then
        verify(deviceRepository, times(1)).delete(device);
    }

    @Test
    public void deleteDeviceById_WhenDeviceNotFound_ThrowsException() {
        final Long deviceId = 1L;

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> deviceService.deleteDeviceById(deviceId));
    }

}
