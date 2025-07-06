package com.anitadukai.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record DeviceRequest(
        @NotBlank String name,
        @NotBlank String type,
        @NotBlank String ip,
        @NotBlank String location
) {
    @Builder
    public DeviceRequest {}
}