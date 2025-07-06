package com.anitadukai.response;

import com.anitadukai.model.Device;
import com.anitadukai.type.StatusType;
import lombok.Builder;

public record DeviceResponse(
        Long id,
        String name,
        String type,
        String ip,
        StatusType status,
        String location
) {
    @Builder
    public DeviceResponse {}

    public static DeviceResponse from(final Device device) {
        return DeviceResponse.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .ip(device.getIp())
                .status(device.getStatus())
                .location(device.getLocation())
                .build();
    }
}