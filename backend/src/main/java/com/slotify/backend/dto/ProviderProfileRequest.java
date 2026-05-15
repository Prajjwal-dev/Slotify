package com.slotify.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderProfileRequest {

    private UUID userId;

    private String businessName;;

    private String description;


}
