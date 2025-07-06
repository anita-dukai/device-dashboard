package com.anitadukai.service;

import com.anitadukai.model.Device;
import com.anitadukai.model.StatusHistory;
import com.anitadukai.repository.DeviceRepository;
import com.anitadukai.repository.StatusHistoryRepository;
import com.anitadukai.type.StatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusHistoryService {

    private final DeviceRepository deviceRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    private final Random random = new Random();
    private final StatusType[] statuses = StatusType.values();

    @Transactional(readOnly = true)
    public List<StatusHistory> getStatusHistories(final Long deviceId) {
        return statusHistoryRepository.findByDeviceIdOrderByChangedAtDesc(deviceId);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateDeviceStatuses() {
        final List<Device> devices = deviceRepository.findAll();
        final List<StatusHistory> histories = new ArrayList<>();

        for (final Device device : devices) {
            StatusType newStatus = statuses[random.nextInt(statuses.length)];

            if (device.getStatus() != newStatus) {
                device.setStatus(newStatus);
                deviceRepository.save(device);

                final StatusHistory history = new StatusHistory()
                        .setDevice(device)
                        .setChangedAt(LocalDateTime.now())
                        .setStatus(newStatus);

                histories.add(history);
            }
        }

        statusHistoryRepository.saveAll(histories);
    }

}
