package com.anitadukai.response;

import com.anitadukai.model.StatusHistory;
import com.anitadukai.type.StatusType;
import lombok.Builder;

import java.time.LocalDateTime;

public record StatusHistoryResponse(
        Long id,
        StatusType status,
        LocalDateTime changedAt
) {
    @Builder
    public StatusHistoryResponse {}

    public static StatusHistoryResponse from(final StatusHistory history) {
        return StatusHistoryResponse.builder()
                .id(history.getId())
                .status(history.getStatus())
                .changedAt(history.getChangedAt())
                .build();
    }
}