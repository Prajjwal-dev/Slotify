package com.slotify.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {

    private UUID id;

    private UUID providerId;

    private String name;

    private Integer durationMinutes;

    private BigDecimal price;

    private String description;

}
