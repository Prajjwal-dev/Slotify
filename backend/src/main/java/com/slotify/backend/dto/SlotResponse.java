package com.slotify.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotResponse {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean available;
}
