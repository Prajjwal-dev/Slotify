package com.slotify.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockedDateRequest {

    private UUID providerId;

    private LocalDate blockedDate;

    private String reason;
}
