package com.anitadukai.request;

public record DeviceRequest(
        String name,
        String type,
        String ip,
        String location
) {
}