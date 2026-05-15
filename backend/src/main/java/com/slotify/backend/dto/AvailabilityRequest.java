package com.slotify.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityRequest {

    private UUID providerId;

    private String dayofWeek;

    private LocalTime startTime;

    private LocalTime endTime;


}
