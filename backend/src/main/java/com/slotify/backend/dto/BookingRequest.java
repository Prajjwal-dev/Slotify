package com.slotify.backend.dto;

import com.slotify.backend.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private UUID customerId;

    private UUID providerId;

    private UUID serviceId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String notes;


}
